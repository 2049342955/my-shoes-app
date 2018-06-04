package com.my.business.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.business.common.annotion.AppendRole;
import com.my.business.entity.Role;
import com.my.business.entity.User;
import com.my.business.service.IRoleService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


//@Component
//@Aspect
public class AppendRoleAop  {
    private static final Logger logger = LoggerFactory.getLogger(AppendRoleAop.class);

    @Autowired
    private IRoleService iRoleService;


    @Pointcut("@annotation(com.my.business.common.annotion.AppendRole)")
    public void AppendRole() {
    }

    @Around("AppendRole()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object o = point.proceed();
        if(o instanceof ResponseEntity){
            ResponseEntity responseEntity = (ResponseEntity) o;
            MethodSignature signature = (MethodSignature) point.getSignature();
            AppendRole annotations = signature.getMethod().getAnnotation(AppendRole.class);
            String include[] = annotations.include();
            String fieldId = annotations.value();
            String userId;
            JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(responseEntity.getData()));
            responseEntity.setData(processStore(data, fieldId,include));
            return responseEntity;
        }
        return o;
    }

    private JSONObject processStore(JSONObject sourceDate, String fieldId,String[] include) {
        String userId="";
        if (StringUtils.isEmpty(fieldId)) {
            userId = sourceDate.getString("id");
        }
        if(!StringUtils.isEmpty(userId)){
            Role role = new Role();
            User user = new User();
            user.setId(userId);
            role = iRoleService.select(user);
            if(role!=null && !StringUtils.isEmpty(role.getId())){
                sourceDate.put("role",role);
            }
        }
        return sourceDate;
    }

}
