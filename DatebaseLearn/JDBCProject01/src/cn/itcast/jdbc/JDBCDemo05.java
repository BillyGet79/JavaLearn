package cn.itcast.jdbc;

import java.io.*;
import java.sql.*;

public class JDBCDemo05 {
    public static void main(String[] args) throws Exception{
        //用户输入用户名和密码，判断是否能够登录
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        System.out.println("请输入用户名：");
        in.nextToken();
        String username = in.sval;
        System.out.println("请输入密码：");
        in.nextToken();
        String password = String.valueOf((int) in.nval);
        //1、注册驱动
        //2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb16_jdbc", "root", "123456");
        //3、执行sql
        //这里使用了statement造成了可能出现的SQL注入
        //比如用户输入用户名：ddd，密码：aaa 'or' 1=1
        //这个时候sql语句就变成了：select * from user where username = 'ddd' and password = 'aaa 'or' 1=1'
        //由于or后面的结果永远为真，所以这个语句一定能查询到结果
        //就造成了登录成功
        //解决方案：将statement更改为preparestatement
        //Statement statement = connection.createStatement();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, password);

        System.out.println(preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            out.println("登陆成功");
        } else {
            out.println("登录失败");
        }

        //5、关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
        out.flush();
    }
}
