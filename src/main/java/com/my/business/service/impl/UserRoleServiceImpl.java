package com.my.business.service.impl;

import com.my.business.entity.UserRole;
import com.my.business.mapper.UserRoleMapper;
import com.my.business.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole save(UserRole userRole) {
        if(userRole.getId()==null){
            userRoleMapper.insert(userRole);
        }else{
            userRoleMapper.updateByPrimaryKeySelective(userRole);
        }
        return userRole;
    }

    @Override
    public int delete(UserRole userRole) {
        return userRoleMapper.delete(userRole);
    }
}
