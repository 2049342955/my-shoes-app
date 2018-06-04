package com.my.business.web;


import com.my.business.entity.Discounts;
import com.my.business.entity.Shoes;
import com.my.business.service.IDiscountsService;
import com.my.business.service.IShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.ManagedBean;
import javax.validation.constraints.PastOrPresent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private IDiscountsService iDiscountsService;

    @Autowired
    private IShoesService iShoesService;

    @RequestMapping("/get")
    public Map get(String id) throws IllegalAccessException {
        Discounts discounts = new Discounts();
        discounts.setId(id);
        discounts = iDiscountsService.selectOne(discounts);
        Map map = new HashMap();
        Shoes shoes = new Shoes();
        if(discounts!=null && !StringUtils.isEmpty(discounts.getId())){
            shoes.setId(discounts.getShoesId());
            shoes = iShoesService.selectOne(shoes);
            map = process(shoes,discounts.getPrice());
        }
        return map;
    }

    @PostMapping("/save")
    public Map save(Discounts discounts) throws IllegalAccessException {
        discounts = iDiscountsService.selectOne(discounts);
        Map map =new HashMap();
        Shoes shoes = new Shoes();
        if(discounts!=null && !StringUtils.isEmpty(discounts.getId())){
            shoes.setId(discounts.getShoesId());
            shoes = iShoesService.selectOne(shoes);
            map = process(shoes,discounts.getPrice());
        }
        return map;
    }

    public Map process(Shoes shoes,Double price) throws IllegalAccessException {
        Map map = new HashMap();
        Class clazz = Shoes.class;
        Field[] fileds = clazz.getDeclaredFields();
        for(Field f:fileds){
            f.setAccessible(true);
            String name= f.getName();
            Object o = f.get(shoes);
            map.put(name,o);
        }
        return map;
    }
}
