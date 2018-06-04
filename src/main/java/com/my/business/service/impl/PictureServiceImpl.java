package com.my.business.service.impl;

import com.my.business.entity.Picture;
import com.my.business.mapper.PictureMapper;
import com.my.business.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class PictureServiceImpl implements IPictureService{

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> list(Picture picture) {
        return pictureMapper.select(picture);
    }

    @Override
    public Picture save(Picture picture) {
        if(StringUtils.isEmpty(picture.getId())){
            pictureMapper.insert(picture);
        }else{
            pictureMapper.updateByPrimaryKeySelective(picture);
        }
        return picture;
    }

    @Override
    public int delete(Picture picture) {
        return pictureMapper.delete(picture);
    }
}
