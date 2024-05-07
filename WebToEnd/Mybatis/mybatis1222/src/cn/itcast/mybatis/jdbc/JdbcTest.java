package cn.itcast.mybatis.jdbc;

import java.sql.*;

/**
 * 通过单独的jdbc程序，总结其中的问题
 */
public class JdbcTest {
    public static void main(String[] args) throws Exception {
        //数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "123456");
        //sql语句
        String sql = "select * from user where username = ?";
        //预编译Statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setString(1, "王五");
        ResultSet resultSet = preparedStatement.executeQuery();
        //打印表数据
        while (resultSet.next()) {
            System.out.print(resultSet.getObject("id") + "\t" + resultSet.getObject("username"));
            System.out.println();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
