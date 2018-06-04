package com.my.business.service.impl;

import com.my.business.entity.Role;
import com.my.business.entity.User;
import com.my.business.mapper.RoleMapper;
import com.my.business.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role get(String id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.selectByPrimaryKey(role);
    }

    @Override
    public Role selectOne(Role role) {
        return roleMapper.selectOne(role);
    }

    @Override
    public List<Role> list(Role role) {
        return roleMapper.select(role);
    }

    @Override
    public Role save(Role role) {
        if(role.getId()==null){
            roleMapper.insert(role);
        }else{
            roleMapper.updateByPrimaryKeySelective(role);
        }
        return role;
    }

    @Override
    public int delete(Role role) {
        return roleMapper.delete(role);
    }

    @Override
    public Role select(User user) {
        return roleMapper.selectByUser(user);
    }
}
