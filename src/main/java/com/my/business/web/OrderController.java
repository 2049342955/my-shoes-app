package com.my.business.web;

import com.my.business.entity.Orders;
import com.my.business.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("/get")
    public Orders get(String id){
        return iOrderService.get(id);
    }

    @GetMapping("/selectOne")
    public Orders selectOne(Orders order){
        return iOrderService.selectOne(order);
    }

    @GetMapping("/list")
    public List<Orders> list(Orders order){
        return iOrderService.list(order);
    }

    @PostMapping("/save")
    public Orders save(@RequestBody Orders order){
        return iOrderService.save(order);
    }

    @GetMapping("/getPictures")
    public List<Double> getPictures(){
        List<Double> result =new ArrayList<>();
        Double[] temp ={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        List<Map> list = new ArrayList<>();
        list = iOrderService.getPictures();
        if(list!=null && list.size()>0){
            for(Map map:list){
                String month= (String) map.get("month");
                int t = Integer.parseInt(month);
                temp[t-1]= (Double) map.get("total");
            }
        }
        result = Arrays.asList(temp);
        return result;
    }

    @GetMapping("/delete")
    public boolean delete(String id){
        Orders order = new Orders();
        order.setId(id);
        return iOrderService.delete(order)>0;
    }
}
