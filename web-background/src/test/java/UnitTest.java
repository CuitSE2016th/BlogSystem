import com.bs.ssh.beans.Permission;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.PermissionDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.IDUtils;
import com.bs.ssh.utils.RedisUtils;
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

    @Autowired
    RedisUtils redis;

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
        user1.setRoleID("r001");

        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setCreateTime(System.currentTimeMillis());


//        user1 = userDao.findOne("from User");
//        user1.setNickname("测试者");
//        userDao.update(user1);
//        userDao.delete(user1);
        userDao.insert(user1);
    }

//    @Test
    public void follow() {
//        List<User> users = new LinkedList<>();
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

    @Test
    public void timestamp() {
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }

    @Test
    public void redis() {
        redis.set("test1", "value1");
        System.out.println(redis.get("test"));
        System.out.println(redis.exist("test1"));
        redis.remove("test", "test1");
        System.out.println(redis.exist("test"));
        System.out.println(redis.exist("test1"));
    }

    @Test
    public void hash() {
        String salt = HashUtils.getSalt();
        System.out.println("盐:                  " + salt);
        System.out.println("令牌:                 " + HashUtils.getToken());
        System.out.println("Sha1ForPasswordAndSalt:" + HashUtils.sha256("123456Abcdefgo", salt));
    }
}
