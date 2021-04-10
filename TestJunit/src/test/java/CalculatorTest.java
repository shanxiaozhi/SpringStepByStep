import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.Arrays;

/**
 * @author liyan
 * @create 2021-03-2021/3/31-19:20
 */
public class CalculatorTest {
    private Calculator cal;

    @BeforeClass
    public static void bc(){
        System.out.println("befortclass");
    }

    @org.junit.Before
    public void setUp() throws Exception {
        System.out.println("befor");
        cal=new Calculator();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.out.println("down");
    }

    @org.junit.Test
    public void add() {
        System.out.println("add测试");
        Assert.assertEquals(4,cal.add(2,2));
    }

    @org.junit.Test
    public void sub() {
        System.out.println("sub测试");
        Assert.assertEquals(0,cal.sub(2,2));
    }
    @AfterClass
    public static void ac(){
        System.out.println("afterclass");
    }
}