package com.note.service.impl;

import com.note.dao.IUserDao;
import com.note.entity.User;
import com.note.service.IUserService;
import com.note.util.NoteResult;
import com.note.util.UUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service("iUserService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao iUserDao;

    //根据ID查找用户
    @Override
    public NoteResult<User> findByName(String name, String password) {
        //调用Dao中的方法查找用户
        NoteResult<User> result = new NoteResult<>();
        User user = iUserDao.findByName(name);
        if (user == null) {
            result.setStatus(0);
            result.setMsg("没有此用户");
            return result;
        }
        if (!password.equals(user.getCn_user_password())) {
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        result.setStatus(1);
        result.setMsg("登录成功");
        result.setData(user);
        return result;
    }

    //注册
    @Override
    public NoteResult<User> save(String name, String password, String nick) {
        NoteResult<User> result = new NoteResult<User>();
        //根据用户名查找用户信息判断用户是否存在
        User users = iUserDao.findByName(name);
        if (users != null) {
            result.setStatus(0);
            result.setMsg("用户名已存在");
            return result;
        }

        //创建用户并保存
        User user = new User();
        user.setCn_user_name(name);
        user.setCn_user_password(password);
        user.setCn_user_nick(nick);
        //使用工具类创建唯一ID UUid
        String id = UUid.createId();
        user.setCn_user_id(id);
        iUserDao.save(user);

        result.setStatus(1);
        result.setMsg("注册成功");
        result.setData(user);

        return result;
    }

    @Override
    //更改密码
    public NoteResult<Object> change(String userName, String last_password, String final_password) {

        NoteResult<Object> result = new NoteResult<Object>();
        //按照用户名进行查询
        User user = iUserDao.findByName(userName);
        //获取用户密码
        String user_password = user.getCn_user_password();
        //判断密码是否一致
        if (!user_password.equals(last_password)) {
            result.setStatus(1);
            result.setMsg("密码不一致，请重新填写");
            return result;
        } else if (user_password.equals(final_password)) {
            result.setStatus(2);
            result.setMsg("新密码不能和旧密码一致，请重新填写");
            return result;
        } else {
            //返回操作结果
            user.setCn_user_password(final_password);
            iUserDao.change(user);
            result.setStatus(0);
            result.setMsg("密码修改成功");
            return result;
        }
    }
}
