package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springFramework.stereotype.MyBean;
import com.yc.springFramework.stereotype.MyComponentScan;
import com.yc.springFramework.stereotype.MyConfiguration;
import com.yc.springFramework.stereotype.MyPostConstruct;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:57
 */
@MyConfiguration
@MyComponentScan(basePackages = "com.yc.bean")
public class MyAppConfig {
    @MyBean
    public HelloWorld helloWorld1(){
      HelloWorld helloWorld=  new HelloWorld();
      helloWorld.name="hhhhh";
      return helloWorld;
    }

}
