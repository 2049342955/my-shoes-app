package com.my.business.service.impl;

import com.my.business.entity.Orders;
import com.my.business.entity.Shoes;
import com.my.business.mapper.OrdersMapper;
import com.my.business.mapper.ShoesMapper;
import com.my.business.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("ALL")
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private ShoesMapper shoesMapper;



    @Override
    @Transactional
    public Orders save(Orders order) {
        if(StringUtils.isEmpty(order.getId())){
            order.setDate(new Date());
            orderMapper.insert(order);
            Shoes shoes = new Shoes();
            shoes.setId(order.getShoesId());
            shoes.setStock(order.getNumber());
            shoesMapper.updateStock(shoes);
        }else{
            orderMapper.updateByPrimaryKeySelective(order);
        }
        return order;
    }

    @Override
    public List<Orders> list(Orders order) {
        return orderMapper.select(order);
    }

    @Override
    public int delete(Orders order) {
        return orderMapper.delete(order);
    }

    @Override
    public Orders get(String id) {
        Orders order = new Orders();
        order.setId(id);
        return orderMapper.selectByPrimaryKey(order);
    }

    @Override
    public Orders selectOne(Orders order) {
        return orderMapper.selectOne(order);
    }

    @Override
    public List<Map> getPictures() {
        return orderMapper.getPictures();
    }
}
