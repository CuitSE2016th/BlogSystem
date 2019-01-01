package com.bs.ssh.service.user.impl;

import com.bs.ssh.bean.IndexArticle;
import com.bs.ssh.bean.JsonBody;
import com.bs.ssh.bean.PageBean;
import com.bs.ssh.dao.FollowDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.user.UserService;
import com.bs.ssh.utils.*;

import org.apache.struts2.ServletActionContext;
import org.jboss.jandex.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Resource private RoleDao roleDao;

    @Resource private FollowDao followDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public JsonBody<Object> login(String identity, String password) {
        User user = userDao.findByIdentity(identity);
        JsonBody<Object> body = new JsonBody<>();
        if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户不存在");
        } else if (!user.getPassword().equals(HashUtils.sha256(password, user.getSalt()))) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户名或密码不正确");
        } else {
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setMessage("登录成功");
            String token = HashUtils.getToken();
            //保存token到redis服务器
            String userJson = JsonUtil.toJson(user);
            RedisUtils.set(token, userJson);
            //获取角色
            String role = roleDao.findOne("from Role where id=?", user.getRoleId()).getName();
            Map<String, String> map = new HashMap<>();
            map.put("uid", user.getId());
            map.put("token", token);
            map.put("role", role);
            body.setData(map);
            user.setLastLoginTime(System.currentTimeMillis());
            userDao.update(user);
        }
        return body;
    }

    @Override
    public JsonBody<User> getUserInfo(String identity, String token) {
        User user = userDao.findByIdentity(identity);
        JsonBody<User> body = new JsonBody<>();
        if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("用户不存在");
        } else if (!isTokenValid(token)) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("Token 出错");
        } else {
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setMessage("SUCCESS");
            body.setData(user);
        }
        return body;
    }

    @Override
    public JsonBody<User> updateUserInfo(User tempUser, String identity, String token) {
        User user = userDao.findByIdentity(identity);
        JsonBody<User> body = new JsonBody<>();
        if (!isTokenValid(token)) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("token is error");
        } else if (user == null) {
            body.setCode(Constants.RESPONSE_FAILED);
            body.setMessage("The user does not exist.");
        } else {
            if (StringUtils.isNotBlank(tempUser.getNickname())) {
                user.setNickname(tempUser.getNickname());
            }

            if (StringUtils.isNotBlank(tempUser.getSex())) {
                user.setSex(tempUser.getSex());
            }

            if (StringUtils.isNotBlank(tempUser.getHeadPortrait())) {

                //删掉旧头像
                
                user.setHeadPortrait(tempUser.getHeadPortrait());
            }


            userDao.insert(user);
            body.setCode(Constants.RESPONSE_SUCCEED);
            body.setData(user);
        }
        return body;
    }

    @Override
    public String updateUserPortrait(File pic, String savePath, String picFileName, String identity) throws IOException {


        //如果原来有图片的话需要删掉
        User user = userDao.findByIdentity(identity);
        if (user == null){
            return null;
        }

        if(user.getHeadPortrait() != null) {
            File oldAvatarFile = new File( ServletActionContext.getServletContext()
                    .getRealPath(user.getHeadPortrait()));
            System.out.println("文件删除结果" +   oldAvatarFile.delete());
        }

        //以服务器的文件保存地址和原文件名建立上传文件输出流
        File path = new File(savePath);

        if (!path.exists()) {
            path.mkdir();
        }

        picFileName = user.getId() + System.currentTimeMillis() + picFileName.substring(picFileName.indexOf('.'));

        FileOutputStream fos =new  FileOutputStream(savePath
                + File.separator + picFileName);
        FileInputStream fis = new  FileInputStream(pic);
        byte[] buffer = new byte[1024];

        int len = fis.read(buffer);
        while ((len) > 0) {
            fos.write(buffer, 0, len);
            len = fis.read(buffer);
        }
        fos.close();

        return Constants.FILE_IMAGE_RELATIVE_PATH + "/" + picFileName;
    }

    @Override
    public int registUser(String email, String password) {
        User user = new User();

        if (RegexString.ExecRegex(email, RegexString.regex_UserEmail)) {
            user.setEmail(email);
        } else {
            user.setPhone(email);
        }

        //获得盐值
        String salt = HashUtils.getSalt();
        user.setSalt(salt);

        //通过SHA256盐值加密
        String sha1Password = HashUtils.sha256(password, salt);
        user.setPassword(sha1Password);

        //获取用户的ID
        String userID = IDUtils.UserID();
        user.setId(userID);

        user.setRoleId(1);

        user.setCreateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());

        int flag = userDao.saveUser(user);

        return flag;
    }

    @Override
    public int isExistEmail(String emailOrPhone) {
        User user = userDao.selectOneByEmail(emailOrPhone);
        return user != null ? 1 : 0;
    }

    @Override
    public int isExistPhone(String emailOrPhone) {
        User user = userDao.selectOneByPhone(emailOrPhone);
        return user != null ? 1 : 0;
    }

    @Override
    public boolean isTokenValid(String token) {
        return token != null;
    }

    @Override
    public PageBean<List> getArticlePage(int pn, int pNum) {
        PageBean<List> pageBean = new PageBean<>();

        int articleNum = userDao.selectCountArticlePage();

        if(pn > (articleNum / pNum)){
            pn = articleNum / pNum;
        }

        pageBean.setCurrentPage(pn);
        pageBean.setPageSize(pNum);
        pageBean.setRecordCount(articleNum);

        List articles = userDao.getArticleforPage(pn, pNum);
        List<IndexArticle> articlesNew = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {

            Object[] article = (Object[]) articles.get(i);

            IndexArticle indexArticle = new IndexArticle();
            indexArticle.setId((Integer) article[0]);
            indexArticle.setAuthorId((String) article[1]);
            indexArticle.setTitle((String) article[2]);
            indexArticle.setContent((String) article[3]);
            indexArticle.setStatus(Integer.parseInt(article[4].toString()));
            indexArticle.setCreateTime(Long.valueOf(article[5].toString()));
            indexArticle.setImageUrl((String) article[6]);
            indexArticle.setLikeCount(Integer.parseInt(article[7].toString()));
            indexArticle.setCommCount(Integer.parseInt(article[8].toString()));

            indexArticle.setTime(DateUtils.getDateStringFromLong(Long.valueOf(article[5].toString())));

            articlesNew.add(indexArticle);
        }




        pageBean.setResult(articlesNew);

        return pageBean;
    }

    @Override
    public List<User> getUserFollows(String userId) {

        List<User> userFollows = userDao.getUserFollows(userId);

        return userFollows;
    }

    @Override
    public List<User> getUserFollowedUser(String userId) {

        List<User> userFollows = userDao.getUserFollowedUser(userId);
        return userFollows;
    }

    @Override
    public PageBean<List> getUserLikeArticles(String userId, Integer pageNo) {

        PageBean<List> pageBean = new PageBean();

        int count = userDao.getUserLikeArticlesCount(userId);

        if(pageNo >= (int)(count / 8)){
            pageNo= (int)(count / 8);
        }
        pageBean.setPageSize(8);
        pageBean.setRecordCount(count);
        pageBean.setCurrentPage(pageNo);

        List articleList = userDao.getArticleforPage(pageNo, 8);

        List<IndexArticle> articlesNew = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {

            Object[] article = (Object[]) articleList.get(i);

            IndexArticle indexArticle = new IndexArticle();
            indexArticle.setId((Integer) article[0]);
            indexArticle.setAuthorId((String) article[1]);
            indexArticle.setTitle((String) article[2]);
            indexArticle.setContent((String) article[3]);
            indexArticle.setStatus(Integer.parseInt(article[4].toString()));
            indexArticle.setCreateTime(Long.valueOf(article[5].toString()));
            indexArticle.setImageUrl((String) article[6]);
            indexArticle.setLikeCount(Integer.parseInt(article[7].toString()));
            indexArticle.setCommCount(Integer.parseInt(article[8].toString()));

            indexArticle.setTime(DateUtils.getDateStringFromLong(Long.valueOf(article[5].toString())));

            articlesNew.add(indexArticle);
        }

        pageBean.setResult(articlesNew);

        return pageBean;
    }

    @Override
    public boolean isFollow(String follower, String following) {
        return followDao.isFollow(follower, following);
    }


}
