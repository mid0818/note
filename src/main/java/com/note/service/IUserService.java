package com.note.service;

import com.note.util.NoteResult;
import com.note.entity.User;
import com.note.util.NoteResult;

public interface IUserService {

    //根据ID查找用户信息
    public NoteResult<User> findByName(String name, String password);

    //添加用户
    public NoteResult<User> save(String name, String password, String nick);

    //根据用户ID修改密码
    public NoteResult<Object> change(String userName, String last_password, String final_password);
}
