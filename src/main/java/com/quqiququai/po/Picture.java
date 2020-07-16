package com.quqiququai.po;

import javax.persistence.*;

@Entity
@Table(name = "t_pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String picUrl;

    @ManyToOne
    private Gift gift;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", picUrl='" + picUrl + '\'' +
                ", gift=" + gift +
                '}';
    }
}
