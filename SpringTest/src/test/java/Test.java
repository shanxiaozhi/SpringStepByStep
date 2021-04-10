import com.yc.AppConfig;
import com.yc.SpringTest;
import com.yc.bean.HelloWorld;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class Test {

    private HelloWorld helloWorld;

    @org.junit.Test
    public void test(){
        System.out.println("aaa");
    }
}
