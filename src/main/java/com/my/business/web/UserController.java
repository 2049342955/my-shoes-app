package com.my.business.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.my.business.common.ResponseEntity;
import com.my.business.common.annotion.AppendRole;
import com.my.business.entity.Role;
import com.my.business.entity.User;
import com.my.business.service.IRoleService;
import com.my.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRoleService iRoleService;


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User get(String id){
        return iUserService.get(id);
    }

    @GetMapping("/selectOne")
    public User selectOne(User user){
        return iUserService.selectOne(user);
    }

    @GetMapping("/list")
    public List<User> list(User user){
        return iUserService.list(user);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return iUserService.save(user);
    }

    @RequestMapping("/delete")
    public boolean delete(String id){
        User user = new User();
        user.setId(id);
        return iUserService.delete(user)>0;
    }

    @GetMapping("/login")
    //@AppendRole
    public ResponseEntity<User> login(User user) throws Exception {
        user = iUserService.selectOne(user);
        Role role = iRoleService.select(user);
        ResponseEntity responseEntity = new ResponseEntity();
        if(user!=null && !StringUtils.isEmpty(user.getId())){
            if(role!=null && !StringUtils.isEmpty(role.getId())){
                responseEntity.setCode("sucess");
                responseEntity.setData(process(user,role));
            }else{
                responseEntity.setData(user);
            }
        }
        return responseEntity;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return iUserService.save(user);
    }

    @GetMapping("/role")
    public ResponseEntity<User> getRole(User user){
        ResponseEntity<User> responseEntity = new ResponseEntity<>();
        responseEntity.setCode("sucess");
        responseEntity.setData(iUserService.selectOne(user));
        return responseEntity;
    }

    public Map process(User user,Role role) throws IllegalAccessException {
        Class clazz = User.class;
        Field[] field=clazz.getDeclaredFields();
        Map map = new HashMap();
        for(Field f:field){
            f.setAccessible(true);
            map.put(f.getName(),f.get(user));
        }
        map.put("role",role);
        return map;
    }
}
