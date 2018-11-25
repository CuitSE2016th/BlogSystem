import com.bs.ssh.beans.Article;
import com.bs.ssh.beans.PageBean;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.service.admin.impl.ArticleAdminServiceImpl;
import com.bs.ssh.service.admin.impl.UserAdminServiceImpl;
import com.bs.ssh.service.root.impl.RootServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    @Test
    public void test1(){

        PageBean allUserToPageBean = rootService.getAllUserToPageBean(1, 15);
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

        int userByIdentity = rootService.updateUserTypeByUserID("45253402423", "r004");
        System.out.println("------------------------------------");
        System.out.println(userByIdentity);
        System.out.println("------------------------------------");

    }

    @Test
    public void testdeleteUserByUserID(){

        int userByIdentity = userAdminService.deleteUserByUserID("24448572271");
        System.out.println("------------------------------------");
        System.out.println(userByIdentity);
        System.out.println("------------------------------------");

    }

    @Test
    public void testgetUserByUserID(){

        String  userByUserID= userAdminService.getUserByUserID("test2@163.com");
        System.out.println("------------------------------------");
        System.out.println(userByUserID);
        System.out.println("------------------------------------");

    }

    @Test
    public void testArticleGet(){

        int articleByArticleID = articleAdminService.setArticleStatus("1", 400);
        System.out.println("------------------------------------");
        System.out.println(articleByArticleID);
        System.out.println("------------------------------------");

    }

    @Test
    public void testArticlePage(){

        PageBean page = articleAdminService.getArticlesForPage(1, 15);
        System.out.println("------------------------------------");
        System.out.println(page);
        System.out.println("------------------------------------");

    }

    @Test
    public void testWhile(){

       int i = 100;
       while(i > 0){
           if(i == 98){
               System.out.println("tiaochu");
               break;
           }else{
               System.out.println("-----");
           }
           i--;
       }
       i = 1000;

    }








}
