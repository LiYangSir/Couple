package com.quqiququai.service;

import com.quqiququai.dao.PictureRepository;
import com.quqiququai.po.Gift;
import com.quqiququai.po.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<Picture> getGiftPicUrl() {
        return pictureRepository.findByGiftNullOrderByIdDesc();
    }

    @Transactional
    @Override
    public Picture savePic(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture getPic(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deletePic(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public List<Picture> getPicByGift(Long id) {
        return pictureRepository.findByGift_IdOrderByIdDesc(id);
    }

    @Override
    public Picture getTopPicByGiftId(Long id) {
        return pictureRepository.findFirstByGift_Id(id);
    }
}
