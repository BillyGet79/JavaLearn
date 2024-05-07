package cn.itcast.jdbc;

import java.sql.*;

public class JDBCDemo03 {
    public static void main(String[] args) throws Exception{
        //1、注册驱动
        //2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb16_jdbc", "root", "123456");
        //3、执行sql
        //3.1增加数据
        Statement statement = connection.createStatement();
//        int rows1 = statement.executeUpdate("insert into student values(null, '鲁智深', 40)");
//        System.out.println(rows1);
        //3.2修改数据
//        int rows2 = statement.executeUpdate("update student set age = 48 where sname = '武松'");
//        System.out.println(rows2);
        //3.3删除数据
        int rows3 = statement.executeUpdate("delete from student where sname = '鲁智深'");
        System.out.println(rows3);

        //5、关闭连接
        statement.close();
        connection.close();
    }
}
