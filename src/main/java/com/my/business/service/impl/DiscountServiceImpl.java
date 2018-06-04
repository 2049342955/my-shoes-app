package com.my.business.service.impl;


import com.my.business.entity.Discounts;
import com.my.business.mapper.DiscountsMapper;
import com.my.business.service.IDiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DiscountServiceImpl implements IDiscountsService{

    @Autowired
    private DiscountsMapper discountsMapper;


    @Override
    public Discounts save(Discounts discounts) {
        if(StringUtils.isEmpty(discounts.getId())){
            discountsMapper.insert(discounts);
        }else{
            discountsMapper.updateByPrimaryKeySelective(discounts);
        }
        return discounts;
    }

    @Override
    public int delete(Discounts discounts) {
        return discountsMapper.delete(discounts);
    }

    @Override
    public List<Discounts> list(Discounts discounts) {
        return discountsMapper.select(discounts);
    }

    @Override
    public Discounts selectOne(Discounts discounts) {
        return discountsMapper.selectOne(discounts);
    }
}
