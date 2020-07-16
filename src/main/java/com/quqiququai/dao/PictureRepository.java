package com.quqiququai.dao;

import com.quqiququai.po.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findByGift_IdOrderByIdDesc(Long id);

    List<Picture> findByGiftNullOrderByIdDesc();

    void deleteById(Long id);

    Picture findFirstByGift_Id(Long id);
}
