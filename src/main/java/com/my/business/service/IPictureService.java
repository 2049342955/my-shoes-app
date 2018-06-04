package com.my.business.service;

import com.my.business.entity.Picture;

import java.util.List;

public interface IPictureService {
    List<Picture> list(Picture picture);
    Picture save(Picture picture);
    int delete(Picture picture);
}
