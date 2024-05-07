package cn.itcast.jdbc;

import java.sql.*;

public class JDBCDemo02 {
    public static void main(String[] args) throws Exception{
        //1、注册驱动
        //2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb16_jdbc", "root", "123456");
        //3、执行sql
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");

        //获取表的列数
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        //4、处理结果集
        while (resultSet.next()) {  //获取每一行数据
            //获取每一列数据
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getObject(i) + "\t");
            }
            System.out.println();
        }
        //5、关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
