package com.my.business.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.business.common.annotion.AppendRole;
import com.my.business.common.annotion.HandleType;
import com.my.business.entity.Picture;
import com.my.business.entity.Shoes;
import com.my.business.service.IPictureService;
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

import javax.annotation.Resource;
import java.util.List;

@Component
@Aspect
public class HandleTypeAop {
    private static final Logger logger = LoggerFactory.getLogger(HandleTypeAop.class);

    @Resource
    private IPictureService iPictureService;

    @Pointcut("@annotation(com.my.business.common.annotion.HandleType)")
    public void HandleType() {
    }

    @Around("HandleType()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object o = point.proceed();
        if(o instanceof ResponseEntity){
            ResponseEntity responseEntity = (ResponseEntity) o;
            MethodSignature signature = (MethodSignature) point.getSignature();
            HandleType annotations = signature.getMethod().getAnnotation(HandleType.class);
            String value = annotations.value();
            ReturnType type = annotations.returnType();
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            ResponseEntity result = new ResponseEntity();
            if(ReturnType.LIST.equals(type)){
                jsonArray = JSONObject.parseArray(JSONObject.toJSONString(responseEntity.getData()));
                JSONArray json = new JSONArray();
                if(jsonArray!=null){
                    for (Object e : jsonArray) {
                        JSONObject sourceData = JSON.parseObject(JSONObject.toJSONString(e));
                        json.add(process(sourceData, value));
                    }
                }
                result.setData(json);
            }else{
                data = JSONObject.parseObject(JSONObject.toJSONString(responseEntity.getData()));
                result.setData(process(data,value));
            }
            return result;
        }
        return o;
    }

    private JSONObject process(JSONObject sourceDate,String value){
        if("true".equals(sourceDate.getString("type"))){
            sourceDate.put("type","男鞋");
        }else{
            sourceDate.put("type","女鞋");
        }
        if(!StringUtils.isEmpty(value) && "picture".equals(value)){
            Picture picture = new Picture();
            picture.setSid(sourceDate.getString("id"));
            List<Picture> list = iPictureService.list(picture);
            if(list!=null && list.size()>0){
                sourceDate.put("picture",list);
            }
        }
        return sourceDate;
    }

}
