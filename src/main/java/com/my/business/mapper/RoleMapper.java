package com.my.business.mapper;

import com.my.business.entity.Role;
import com.my.business.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface RoleMapper extends Mapper<Role> {
    Role selectByUser(@Param("user") User user);
}