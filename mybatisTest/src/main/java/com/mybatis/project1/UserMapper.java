package com.mybatis.project1;

import java.util.List;

public interface UserMapper {
    //获取全部对象
    List<User> getUsers();

    User getUserById(int id);

    int insertUser(User user);


}
