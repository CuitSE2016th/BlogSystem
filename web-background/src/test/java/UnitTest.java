

import com.bs.ssh.beans.Permission;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.Token;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.PermissionDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.dao.TokenDao;
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

    @Resource
    private TokenDao tokenDao;

//    @Test
    public void initRoleAndPermission(){
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

    @Test
    public void initUserAndToken(){
        User user1 = new User();
        user1.setId(IDUtils.UserID());
        user1.setNickname("测试者1");
        user1.setEmail("test1@163.com");
        user1.setPhone("123456");
        user1.setSalt(HashUtils.getSalt());
        user1.setPassword(HashUtils.hashBySha1("123456"+user1.getSalt()));
        user1.setSex("F");
        user1.setRoleID("r001");

        Token token = new Token();
        token.setId("t001");
        token.setValue(HashUtils.getToken());
        token.setCreateTime(System.currentTimeMillis());
        token.setExpireTime(System.currentTimeMillis());
        token.setUpdateTime(System.currentTimeMillis());
        user1.setToken(token);
        token.setUser(user1);

        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setCreateTime(System.currentTimeMillis());

//        System.out.println(userDao.findOne("from User").getToken().getId());
//        user1 = userDao.findOne("from User");
//        userDao.delete(user1);
        userDao.insert(user1);
    }


    @Test
    public void timestamp(){
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }

    @Test
    public void redis(){
        redis.set("test1", "value1");
        System.out.println(redis.get("test"));
        System.out.println(redis.exist("test1"));
        redis.remove("test","test1");
        System.out.println(redis.exist("test"));
        System.out.println(redis.exist("test1"));
    }

    @Test
    public void hash(){
        String salt = HashUtils.getSalt();
        System.out.println("Salt:                  " + salt);
        System.out.println("Token:                 " + HashUtils.getToken());
        System.out.println("Sha1ForPassword:       " + HashUtils.hashBySha1("123456Abcdefgo"));
        System.out.println("Sha1ForPasswordAndSalt:" + HashUtils.hashBySha1("123456Abcdefgo" + salt));
    }
}
