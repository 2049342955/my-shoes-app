package com.my.business.mapper;

import com.my.business.entity.Orders;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface OrdersMapper extends Mapper<Orders> {
    List<Map> getPictures();
}