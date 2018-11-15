import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create By ZZY on 2018/11/14
 */
public class zzytest {

    @Test
    public void testsplit(){

        String email = "1007610491@qq.com";
        String[] split = email.split("@");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }



    }

}
