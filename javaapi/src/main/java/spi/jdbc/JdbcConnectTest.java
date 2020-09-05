package spi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/goods,'root','123456'");
        Statement stmt=conn.createStatement();

        stmt.executeUpdate("String sql");

        stmt.close();
        conn.close();

    }
}
