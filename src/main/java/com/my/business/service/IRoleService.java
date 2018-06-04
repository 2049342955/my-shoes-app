package com.my.business.service;

import com.my.business.entity.Role;
import com.my.business.entity.User;

import java.util.List;

public interface IRoleService {
    Role get(String id);
    Role selectOne(Role role);
    List<Role> list(Role role);
    Role save(Role role);
    int delete(Role role);
    Role select(User user);
}
