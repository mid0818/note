package com.note.dao;

import com.note.entity.User;

public interface IUserDao {

    //根据用户名查找用户
    public User findByName(String name);

    //添加用户
    public void save(User user);

    //修改密码
    public void change(User user);

}
