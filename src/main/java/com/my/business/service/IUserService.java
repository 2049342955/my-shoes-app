package com.my.business.service;

import com.github.pagehelper.PageInfo;
import com.my.business.entity.User;

import java.util.List;

public interface IUserService {
    User get(String id);
    User selectOne(User user);
    List<User> list(User user);
    PageInfo<User> query(User user);
    User save(User user);
    int delete(User user);
}
