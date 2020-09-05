package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//成功连入mysql数据库，并插入一条数据。
//可观察DriverManager的spi机制，以及com.mysql.jdbc.Driver把自己注册到DriverManager中。
public class Test {
    public static void main(String[] args) throws SQLException {
        DriverManager.println("");
        String url = "jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        Connection conn = DriverManager.getConnection(url,"root","123456");
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT USER(`id`,`name`,`pwd`) VALUES (7,'小黄','123456')");
        stmt.close();
        conn.close();
    }
}
