package cn.itcast.mybatis.first;

import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 入门程序
 */
public class MybatisFirst {
    //根据id查询用户信息，得到一条记录
    @Test
    public void findUserByIdTest() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //通过SqlSession操作数据库
        //第一个参数：映射文件中statement的id，等于namespace+"."+statement的id
        //第二个参数，指定和映射文件中所匹配的parameterType类型的参数
        //返回的结果是与映射文件中所匹配的resultType类型的对象
        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);

        //释放资源
        sqlSession.close();
    }

    //根据用户名称模糊查询用户列表
    @Test
    public void findUserByNameTest() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("test.findUserByName", "小明");
        System.out.println(list);
        sqlSession.close();
    }

    //添加用户信息
    @Test
    public void insertUserTest() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //插入用户对象
        User user = new User("王小军", "1", new Date(), "河南郑州");
        sqlSession.insert("test.insertUser", user);

        //提交事务
        sqlSession.commit();

        //获取用户信息主键
        System.out.println(user.getId());
        //关闭会话
        sqlSession.close();
    }

    //根据id删除用户信息
    @Test
    public void deleteUserByIdTest() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //传入id删除用户
        sqlSession.delete("test.deleteUser", 28);

        //提交事务
        sqlSession.commit();
        //关闭会话
        sqlSession.close();
    }

    //根据id更新用户信息
    @Test
    public void updateUserByIdTest() throws Exception {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //传入id更新用户
        User user = new User();
        user.setId(27);
        user.setUsername("王大军");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("河南郑州");
        sqlSession.delete("test.updateUser", user);

        //提交事务
        sqlSession.commit();
        //关闭会话
        sqlSession.close();
    }
}
