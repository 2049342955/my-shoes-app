package com.my.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.my.business.entity.User;
import com.my.business.entity.UserRole;
import com.my.business.mapper.UserMapper;
import com.my.business.mapper.UserRoleMapper;
import com.my.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User get(String id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectByPrimaryKey(user);
    }

    @Override
    public User selectOne(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public List<User> list(User user) {
        return userMapper.select(user);
    }

    @Override
    public PageInfo<User> query(User user) {
        return null;
    }

    @Override
    @Transactional
    public User save(User user) {
        if(user.getId()==null){
            userMapper.insert(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId("bb2b8f2555f411e88c4954a050ae6420");
            userRoleMapper.insert(userRole);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }

    @Override
    public int delete(User user) {
        return userMapper.delete(user);
    }
}
