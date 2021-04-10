package com.yc.annotation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-21:17
 */
@DbConnection(url = "jdbc:mysql://localhost:3306/test",driverClass = "com.mysql.cj.jdbc.Driver")
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class c=Test.class;
        DbConnection dbc = (DbConnection) c.getAnnotation(DbConnection.class);
        String url = dbc.url();
        String driverClass = dbc.driverClass();
        String user = dbc.user();
        String passWord = dbc.passWord();

        Class.forName(driverClass);
        Connection connection= DriverManager.getConnection(url,user,passWord);
        System.out.println(connection);
    }


}
