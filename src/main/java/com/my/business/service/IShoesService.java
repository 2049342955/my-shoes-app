package com.my.business.service;

import com.github.pagehelper.PageInfo;
import com.my.business.entity.Orders;
import com.my.business.entity.Shoes;
import org.hibernate.criterion.Order;

import java.util.List;

public interface IShoesService {
    Shoes get(String id);
    Shoes selectOne(Shoes shoes);
    List<Shoes> list(Shoes shoes);
    PageInfo<Shoes> query(Shoes shoes);
    Shoes save(Shoes shoes);
    int delete(Shoes shoes);
    List<Shoes> getHome(Orders orders);
}
