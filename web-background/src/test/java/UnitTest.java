import com.bs.ssh.beans.Article;
import com.bs.ssh.beans.Permission;
import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.User;
import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.dao.PermissionDao;
import com.bs.ssh.dao.RoleDao;
import com.bs.ssh.dao.UserDao;
import com.bs.ssh.dao.impl.BaseDaoImpl;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Test
    @Transactional
    public void page(){
        Pageable pageable = new PageRequest(0, 3);

        System.out.println(JsonUtil.toJsonExposed(
                userBaseDao.findAll(pageable, "from User where sex=?", "M")
        ));
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
    @Transactional
    public void gson(){
        User user = userDao.findByIdentity("13881705154");
        Role role = user.getRole();
        System.out.println(role.getName());
        System.out.println(JsonUtil.toJsonExposed(user));
    }


}
