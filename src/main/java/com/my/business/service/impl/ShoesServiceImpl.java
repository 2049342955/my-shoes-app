package com.my.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.my.business.entity.Orders;
import com.my.business.entity.Picture;
import com.my.business.entity.Shoes;
import com.my.business.mapper.ShoesMapper;
import com.my.business.service.IShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoesServiceImpl implements IShoesService{

    @Autowired
    private ShoesMapper shoesMapper;

    @Override
    public Shoes get(String id) {
        Shoes shoes =new Shoes();
        shoes.setId(id);
        return shoesMapper.selectByPrimaryKey(shoes);
    }

    @Override
    public Shoes selectOne(Shoes shoes) {
        return shoesMapper.selectOne(shoes);
    }

    @Override
    public List<Shoes> list(Shoes shoes) {
        return shoesMapper.select(shoes);
    }

    @Override
    public PageInfo<Shoes> query(Shoes shoes) {
        return null;
    }

    @Override
    @Transactional
    public Shoes save(Shoes shoes) {
        if(shoes.getId()==null){
            shoesMapper.insertSelective(shoes);
        }else{
            shoesMapper.updateByPrimaryKeySelective(shoes);
        }
        return shoes;
    }

    @Override
    public int delete(Shoes shoes) {
        return shoesMapper.delete(shoes);
    }

    @Override
    public List<Shoes> getHome(Orders orders) {
        return shoesMapper.getHome(orders);
    }
}
