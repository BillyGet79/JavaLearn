package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo01 {
    public static void main(String[] args) throws Exception{
        //1、注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb16_jdbc", "root", "123456");
        //3、执行sql
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        //4、处理结果集
        while (resultSet.next()) {  //获取每一行数据
            //获取每一列数据
            //int sid = resultSet.getInt("sid");
            int sid = resultSet.getInt(1);
            String sname = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.println(sid + "\t" + sname + "\t" + age);
        }
        //5、关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
