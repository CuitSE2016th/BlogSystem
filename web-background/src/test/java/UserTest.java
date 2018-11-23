import com.bs.ssh.beans.Article;
import com.bs.ssh.beans.Permission;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.dao.PermissionDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.JsonUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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

        permission.setName("query-blog");
        permission.setCreateTime(System.currentTimeMillis());
        permissions.add(permission);
        role.setPermissions(permissions);

        roleDao.insert(role);
    }

    //        @Test
    public void follow() {
        List<User> users = new LinkedList<>();
//        userDao.findAll("from User").forEach(users::add);
//
//        User user3 = users.get(1);
//        User user2 = users.get(2);
//        List<User> user3Following = new LinkedList<>();
//        user3Following.add(user2);
//        user3.setFollowers(user3Following);
//
//        userDao.update(user3);

    }

    @Resource
    private BaseDao<Article> articleBaseDao;
    //        @Test
    public void article(){
        User user = userDao.findByIdentity("13881705154");
        Article article = new Article();
        article.setAuthor(user);
        article.setContent("hello, there is a new article.");
        article.setStatus(0);
        article.setCreateTime(System.currentTimeMillis());
        articleBaseDao.insert(article);
    }

    @Test
    public void initUserAndToken() {
        User user1 = new User();
        user1.setId(IDUtils.UserID());
        user1.setNickname("egan");
        user1.setEmail("18876124435");
        user1.setSalt(HashUtils.getSalt());
        user1.setPassword(HashUtils.hashBySha256("123456" + user1.getSalt()));
        user1.setSex("M");
        Role role = new Role();
        role.setId(1);
        user1.setRole(role);

        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setCreateTime(System.currentTimeMillis());


//        user1 = userDao.findOne("from User");
//        user1.setNickname("测试者");
//        userDao.update(user1);
//        userDao.delete(user1);
        userDao.insert(user1);
    }

    //    @Test
    public void starAndLike(){
        User user = userDao.findByIdentity("13881705154");
        System.out.println("uid:" + user.getId());
        user.setNickname("eganchen");

        Article article = articleBaseDao.findOne("from Article");
        System.out.println(article.getContent());
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        user.setStarArticles(articles);
        user.setLikeArticles(articles);
        System.out.println(user.getLikeArticles().get(0).getContent());
        userDao.update(user);
    }

    @Resource
    private UserDao userBaseDao;
    //    @Test
    @Transactional
    public void entity(){
        User user = userBaseDao.findOne("from User where sex=?", "null");
//        logger.fatal(user.getRole().getName());
        logger.fatal(JsonUtil.toJsonExposed(user));
    }

    //    @Test
    public void clear(){
        User user = userDao.findByIdentity("13881705154");
        user.setNickname("egan");
        userDao.update(user);
    }
}
