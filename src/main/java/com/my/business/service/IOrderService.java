package com.my.business.service;

import com.my.business.entity.Orders;
import com.my.business.entity.Shoes;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    Orders save(Orders order);
    List<Orders> list(Orders order);
    int delete(Orders order);
    Orders get(String id);
    Orders selectOne(Orders order);
    List<Map> getPictures();
}
