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
import com.bs.ssh.utils.RedisUtils;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class UnitTest {
    private static Logger logger = LogManager.getLogger(UnitTest.class);

    @Autowired
    private HibernateTemplate template;

    @PersistenceContext
    EntityManager manager;

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;


    //    @Test
    public void initRoleAndPermission() {
        Role role = new Role();
        Permission permission = new Permission();
        List<Permission> permissions = new LinkedList<>();

        role.setId("r001");
        role.setName("user");
        role.setCreateTime(System.currentTimeMillis());

        permission.setId("p001");
        permission.setName("query-blog");
        permission.setCreateTime(System.currentTimeMillis());
        permissions.add(permission);
        role.setPermissions(permissions);

        roleDao.insert(role);
    }

    //    @Test
    public void initUserAndToken() {
        User user1 = new User();
        user1.setId(IDUtils.UserID());
        user1.setNickname("tester3");
        user1.setEmail("test3@163.com");
        user1.setPhone("1234563");
        user1.setSalt(HashUtils.getSalt());
        user1.setPassword(HashUtils.hashBySha256("123456" + user1.getSalt()));
        user1.setSex("M");

        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setCreateTime(System.currentTimeMillis());


//        user1 = userDao.findOne("from User");
//        user1.setNickname("测试者");
//        userDao.update(user1);
//        userDao.delete(user1);
        userDao.insert(user1);
    }

    @Test
    public void follow() {
        List<User> users = new LinkedList<>();
        userDao.findAll("from User").forEach(users::add);

        User user3 = users.get(1);
        User user2 = users.get(2);
        List<User> user3Following = new LinkedList<>();
        user3Following.add(user2);
        user3.setFollowers(user3Following);

        userDao.update(user3);

    }

    @Resource
    private BaseDao<Article> articleBaseDao;
    @Test
    public void article(){
        User user = userDao.findByIdentity("13881705154");
        Article article = new Article();
        article.setId("a001");
        article.setAuthor(user);
        article.setContent("hello, there is a new article.");
        article.setCreateTime(System.currentTimeMillis());
        articleBaseDao.insert(article);
    }

    @Test
    public void starAndLike(){
        User user = userDao.findByIdentity("13881705154");
        Article article = articleBaseDao.findOne("from Article");
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        user.setStarArticles(articles);
        user.setLikeArticles(articles);
        userDao.update(user);
    }

//    @Test
    public void timestamp() {
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }

//    @Test
    public void redis() {
        RedisUtils.set("test1", "value1");
        System.out.println(RedisUtils.get("test"));
        System.out.println(RedisUtils.exist("test1"));
        RedisUtils.remove("test", "test1");
        System.out.println(RedisUtils.exist("test"));
        System.out.println(RedisUtils.exist("test1"));
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
    public void gson(){
        User user = userDao.findByIdentity("13881705154");
        System.out.println(JsonUtil.toJsonExposed(user));
    }
}
