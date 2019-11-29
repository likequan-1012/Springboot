package com.lkq.controller;

import com.lkq.pojo.User;
import com.lkq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Object getList(User user, @RequestParam(defaultValue = "1")int pageNo, @RequestParam(defaultValue = "3")int pageSize){
        return userService.getList(user,pageNo,pageSize);
    }

    @GetMapping("dels")
    public int deletes(String ids) {
        return userService.deletes(ids);
    }

    @GetMapping("Address")
    public Object getCity(Integer code) {
        return userService.getAddress(code);
    }

    @PostMapping("save")
    public Object save(@RequestBody User user) {
        return userService.save(user);
    }


    @GetMapping("initData")
    public Object getAddress(Integer id) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("org",userService.selectOrg());
        map.put("province",userService.getAddress(130000));
        map.put("show",userService.getById(id));
        return map;
    }

    @GetMapping("getById")
    public User getById(Integer id)
    {
        return userService.getById(id);
    }

}
