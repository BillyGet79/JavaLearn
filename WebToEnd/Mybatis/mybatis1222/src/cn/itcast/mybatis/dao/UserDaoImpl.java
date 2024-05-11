package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * UserDao接口实现类
 */
public class UserDaoImpl implements UserDao {

    //需要向dao实现类中注入SqlSessionFactory
    //这里通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findeUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("test.findUserById", id);

        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String name) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("test.findUserByName", name);

        sqlSession.close();

        return list;
    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.insert("test.insertUser", user);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("test.deleteUser", user);

        sqlSession.commit();

        sqlSession.close();

    }
}
