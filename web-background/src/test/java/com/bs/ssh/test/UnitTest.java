package com.bs.ssh.test;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.entity.Role;
import com.bs.ssh.entity.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.dao.impl.BaseDaoImpl;
import com.bs.ssh.service.user.UserArticleService;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.RedisUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class UnitTest {
    private static Logger logger = LogManager.getLogger(UnitTest.class);

    @Resource
    private
    UserDao userDao;

    @Autowired
    private HibernateTemplate template;

    @Qualifier("base")
    @Resource
    BaseDaoImpl<User> userBaseDao;
    @Qualifier("base")
    @Resource
    BaseDaoImpl<Role> roleBaseDao ;

    @Autowired
    UserArticleService articleService;

//    @Test
    @Transactional
    public PageBean page(){
//        Pageable pageable = new PageRequest(0, 3);

        PageRequest request = new PageRequest();
        request.setPageSize(2);
        request.setPageNumber(1);
        PageBean pageBean = articleService.getAllArticle(request);
        return pageBean;
//        System.out.println(pageBean.getResult());
//        System.out.println(JsonUtils.toJsonExposed(
//                pageBean
//        ));
    }


//    @Test
    @Transactional
    public void articlePage(){
//        System.out.println(
//                JsonUtils.toJsonExposed(
//                        articleService.getAllArticle(new PageRequest(0, 2)).getContent()
//                )
//        );
    }

//    @Test
    public void timestamp() {
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }

//    @Test
    public void redis() {
        RedisUtils.set("com.bs.ssh.test.test1", "value1");
        System.out.println(RedisUtils.get("test"));
        System.out.println(RedisUtils.exist("com.bs.ssh.test.test1"));
        RedisUtils.remove("test", "com.bs.ssh.test.test1");
        System.out.println(RedisUtils.exist("test"));
        System.out.println(RedisUtils.exist("com.bs.ssh.test.test1"));
    }

//    @Test
    public void hash() {
        String string = "7617a069207d110efd2c507b304f885e5597ce82a121acf1032ce2a52c793ab8";
        String salt = "A4F0BDFCB77E4B369DEBD4E5BAE9C66FDD551A16A3281C2829787D28B2218310";
        System.out.println("盐:                  " + salt);
        System.out.println("令牌:                 " + HashUtils.getToken());
        System.out.println("Sha1ForPasswordAndSalt:" + HashUtils.sha256("123456Abcdefgo", salt));
    }

//    @Test
    @Transactional
    public void gson(){
        User user = userDao.findByIdentity("13881705154");
    }


}
