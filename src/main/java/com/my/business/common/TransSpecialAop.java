package com.my.business.common;

import com.my.business.common.annotion.AppendRole;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Aspect
public class TransSpecialAop {

    private static final Logger logger = LoggerFactory.getLogger(TransSpecialAop.class);

    @Pointcut("@annotation(com.my.business.common.annotion.TransSpectical)")
    public void TransSpecial(){};

//    @Around(value = "TransSpecial()")
//    public Object around(){
//
//    }

}
