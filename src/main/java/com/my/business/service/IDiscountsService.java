package com.my.business.service;

import com.my.business.entity.Discounts;

import java.util.List;

public interface IDiscountsService {
    Discounts save(Discounts discounts);
    int delete(Discounts discounts);
    List<Discounts> list(Discounts discounts);
    Discounts selectOne(Discounts discounts);
}
