package com.my.business.service;

import com.my.business.entity.UserRole;

public interface IUserRoleService {
    UserRole save(UserRole userRole);
    int delete(UserRole userRole);
}
