package com.note.controller;


import com.note.entity.User;
import com.note.service.IUserService;
import com.note.util.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
   private IUserService iUserService;

    //登录
    @RequestMapping("/login.do")
    @ResponseBody
    public  NoteResult<User> findByName(String name, String password){
        NoteResult<User> result = iUserService.findByName(name, password);
        return result;
    }

    @RequestMapping("/add.do")
    @ResponseBody
    //注册
    public NoteResult<User> add(String name,String password,String nike){
        NoteResult<User> result = iUserService.save(name, password, nike);
        return result;
    }
    //修改密码
    @RequestMapping("/change.do")
    @ResponseBody
    public NoteResult<Object> change(String userName,String last_password,String final_password){
        NoteResult<Object> result = iUserService.change(userName, last_password, final_password);
        return result;
    }

}
