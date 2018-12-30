package com.bs.ssh.test;

import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.UserPlus;
import com.bs.ssh.entity.Article;
import com.bs.ssh.entity.User;

import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.admin.impl.ArticleAdminServiceImpl;
import com.bs.ssh.service.admin.impl.UserAdminServiceImpl;
import com.bs.ssh.service.root.impl.RootServiceImpl;
import com.bs.ssh.service.user.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By ZZY on 2018/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class zzytest {

    @Autowired
    private HibernateTemplate template;

    @Resource
    private UserDao userDao;

    @Autowired
    private RootServiceImpl rootService;

    @Autowired
    private UserAdminServiceImpl userAdminService;

    @Autowired
    private ArticleAdminServiceImpl articleAdminService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test1(){

        PageBean allUserToPageBean = rootService.getAllUserToPageBean(1, 15, 2);
        System.out.println("------------------------------------");
        System.out.println(allUserToPageBean);
        System.out.println("------------------------------------");

    }

    @Test
    public void test2(){

        User userByIdentity = rootService.getUserByIdentity("1007610491@qq.com");
        System.out.println("------------------------------------");
        System.out.println(userByIdentity);
        System.out.println("------------------------------------");

    }

    @Test
    public void testupdateUserTypeByUserID(){

        int userByIdentity = rootService.updateUserTypeByUserID("45253402423", "4");
        System.out.println("------------------------------------");
        System.out.println(userByIdentity);
        System.out.println("------------------------------------");

    }

    @Test
    public void testdeleteUserByUserID(){

        int userByIdentity = userAdminService.deleteUserByUserID("35268860677");
        System.out.println("------------------------------------");
        System.out.println(userByIdentity);
        System.out.println("------------------------------------");

    }

    @Test
    public void getAllUserToPageBean(){

        PageBean allUserToPageBean = userAdminService.getAllUserToPageBean(1, 15);
        System.out.println("------------------------------------");
        System.out.println(allUserToPageBean);
        System.out.println("------------------------------------");

    }

    @Test
    public void getArticleByArticleID(){

        Article articleByArticleID = articleAdminService.getArticleByArticleID("1aaa");
        System.out.println("------------------------------------");
        System.out.println(articleByArticleID);
        System.out.println("------------------------------------");

    }

    @Test
    public void setArticleStatus(){

        int setArticleStatus = articleAdminService.setArticleStatus("1", 500);
        System.out.println("------------------------------------");
        System.out.println(setArticleStatus);
        System.out.println("------------------------------------");

    }

    @Test
    public void getArticlesForPage(){

        User userByUserID = userAdminService.getUserByUserID("986204478@qq.com");
        System.out.println("------------------------------------");
        System.out.println(userByUserID);
        System.out.println("------------------------------------");

    }


    @Test
    public void getIndexArticlesForPage(){

        System.out.println("------------------------------------");
        System.out.println(userService.getArticlePage(1, 8));
        System.out.println("------------------------------------");
    }


}
