package com.bs.ssh.test;

import com.bs.ssh.beans.*;
import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.dao.PermissionDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.IDUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class UserTest {
    private static Logger logger = LogManager.getLogger(UnitTest.class);



    @PersistenceContext
    EntityManager manager;

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    //        @Test
    public void initRoleAndPermission() {
        Role role = new Role();
        Permission permission = new Permission();
        List<Permission> permissions = new LinkedList<>();

        role.setName("user");
        role.setCreateTime(System.currentTimeMillis());

//        permission.setName("query-blog");
//        permission.setCreateTime(System.currentTimeMillis());
//        permissions.add(permission);
//        role.setPermissions(permissions);

        roleDao.insert(role);
    }

    @Resource
    BaseDao<Follow> followDao;
    @Test
    @Transactional
    public void follow() {
//        List<User> users = new LinkedList<>();
//        PageRequest request = new PageRequest();
//        request.setPageNumber(1);
//        request.setPageSize(2);
//        users.addAll(userDao.findAll(request, "from User"));
//
//        User user1 = users.get(0);
//        User user2 = users.get(1);
        Follow follow = new Follow();
        follow.setFollowerId("17826287827");
        follow.setFollowingId("35268860677");
        follow.setCreateTime(System.currentTimeMillis());
        followDao.insert(follow);
//
//        userDao.update(user3);

    }

    @Resource
    private BaseDao<Article> articleBaseDao;
//    @Test
    @Transactional
    @Rollback(false)
    public void article(){
        User user = userDao.findByIdentity("13881705154");
        Article article = new Article();
        article.setAuthorId(user.getId());
        article.setContent("hello, there is a new article.");
        article.setStatus(0);
        article.setCreateTime(System.currentTimeMillis());
        try {

            articleBaseDao.insert(article);
        }catch (Exception e){
            logger.fatal(e.getCause());
        }
    }

//    @Test
    public void initUserAndToken() {
        User user1 = new User();
        user1.setId(IDUtils.UserID());
        user1.setNickname("egan");
        user1.setEmail("18876124435");
        user1.setSalt(HashUtils.getSalt());
        user1.setPassword(HashUtils.hashBySha256("123456" + user1.getSalt()));
        user1.setSex("M");
        user1.setRoleId(1);

        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setCreateTime(System.currentTimeMillis());


//        user1 = userDao.findOne("from User");
//        user1.setNickname("测试者");
//        userDao.update(user1);
//        userDao.delete(user1);
        userDao.insert(user1);
    }

    @Resource BaseDao<Star> starBaseDao;
    @Resource BaseDao<Like> likeBaseDao;
//        @Test
//        @Transactional
    public void starAndLike(){
        User user = userDao.findByIdentity("13881705154");

        Article article = articleBaseDao.findOne("from Article");
        System.out.println(article.getContent());

        Star star = new Star();
        star.setArticleId(article.getId());
        star.setUserId(user.getId());
        starBaseDao.insert(star);
    }

    @Resource
    private UserDao userBaseDao;
    //    @Test
    @Transactional
    public void entity(){
        User user = userBaseDao.findOne("from User where sex=?", "null");
//        logger.fatal(user.getRole().getName());
//        logger.fatal(JsonUtils.toJsonExposed(user));
    }

    //    @Test
    public void clear(){
        User user = userDao.findByIdentity("13881705154");
        user.setNickname("egan");
        userDao.update(user);
    }
}
