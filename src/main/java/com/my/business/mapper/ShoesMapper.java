package com.my.business.mapper;

import com.my.business.entity.Orders;
import com.my.business.entity.Shoes;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ShoesMapper extends Mapper<Shoes> {
    int updateStock(@Param("shoes") Shoes shoes);
    List<Shoes> getHome(@Param("order") Orders orders);
}