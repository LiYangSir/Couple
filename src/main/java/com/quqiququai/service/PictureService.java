package com.quqiququai.service;

import com.quqiququai.po.Gift;
import com.quqiququai.po.Picture;

import java.util.List;

public interface PictureService {

    List<Picture> getGiftPicUrl();

    List<Picture> getPicByGift(Long id);

    Picture getPic(Long id);

    void deletePic(Long id);

    Picture savePic(Picture picture);

    Picture getTopPicByGiftId(Long id);
}
