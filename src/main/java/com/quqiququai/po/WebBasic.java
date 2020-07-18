package com.quqiququai.po;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_web_basic")
public class WebBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;
    String bottomDescription;
    String urls;

    String indexPictures;

    @Temporal(TemporalType.TIMESTAMP)
    Date missDate = new Date();

    @Transient
    Map<String, String> bottomUrls = new HashMap<>();

    @Transient
    long missTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        this.bottomDescription = bottomDescription;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Date getMissDate() {
        return missDate;
    }

    public void setMissDate(Date missDate) {
        this.missDate = missDate;
    }

    public Map<String, String> getBottomUrls() {
        return bottomUrls;
    }

    public void setBottomUrls(Map<String, String> bottomUrls) {
        this.bottomUrls = bottomUrls;
    }

    public long getMissTime() {
        return missTime;
    }

    public void setMissTime(long missTime) {
        this.missTime = missTime;
    }

    public String getIndexPictures() {
        return indexPictures;
    }

    public void setIndexPictures(String indexPictures) {
        this.indexPictures = indexPictures;
    }

    @Override
    public String toString() {
        return "WebBasic{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", bottomDescription='" + bottomDescription + '\'' +
                ", urls='" + urls + '\'' +
                ", indexPictures='" + indexPictures + '\'' +
                ", missDate=" + missDate +
                ", bottomUrls=" + bottomUrls +
                ", missTime=" + missTime +
                '}';
    }
}
