package com.yc;



import com.yc.biz.Calculator;
import com.yc.junit.*;

/**
 * @author liyan
 * @create 2021-03-2021/3/31-19:43
 */
public class MyCalculatorTest {
    private Calculator cal;

    @MyBeforeClass
    public static void bc(){
        System.out.println("befortclass");
    }

    @MyBefore
    public void setUp() throws Exception {
        System.out.println("befor");
        cal=new Calculator();
    }

    @MyAfter
    public void tearDown() throws Exception {
        System.out.println("down");
    }

    @MyTest
    public void add() {
        System.out.println("add测试");
    }

    @MyTest
    public void sub() {
        System.out.println("sub测试");
    }
    @MyAfterClass
    public static void ac(){
        System.out.println("afterclass");
    }
}
