package com.mybatis.project1;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test1() {
        SqlSession sqlSession = Util.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();

        for (User user:users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void getUserByIdTest() {
        SqlSession sqlSession = Util.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(2);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void insertUserTest() {
        SqlSession sqlSession = Util.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int row = userMapper.insertUser(new User(5,"小毛","123456"));
        System.out.println(row);
        //数据库涉及修改的需要commit
        sqlSession.commit();
        sqlSession.close();
    }

}
