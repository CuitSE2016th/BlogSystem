

import com.bs.ssh.beans.Role;
import com.bs.ssh.beans.Token;
import com.bs.ssh.beans.User;
import com.bs.ssh.utils.HashUtils;
import com.bs.ssh.utils.RedisUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    @Test
    public void Token(){

        String hql = "from Token ";
        List<?> t = template.find(hql);

        Token token = (Token) t.get(0);
        System.out.println(token.getUser().getNickname());
    }

    @Test
    public void roleAndPermission(){
        String hql = "from Role ";
        List<?> roles = template.find(hql);

        Role role = (Role) roles.get(0);
        System.out.println(role.getPermissions().get(0).getName());
    }

    @Test
    public void user(){
        String hql = "from User ";
        List<?> users = template.find(hql);

        User user = (User) users.get(0);
        System.out.println("follower:" + user.getFollower().get(0).getNickname());
        System.out.println("following:" + user.getFollowing().get(0).getNickname());
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
