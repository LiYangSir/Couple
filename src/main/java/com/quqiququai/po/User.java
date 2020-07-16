package com.quqiququai.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String userName;
    private String password;
    private String Description;

    private String location;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Integral> integrals = new ArrayList<>();

    @OneToMany(mappedBy = "publishUser", cascade = CascadeType.REMOVE)
    private List<Gift> publishGifts = new ArrayList<>();

    @OneToMany(mappedBy = "acceptUser", cascade = CascadeType.REMOVE)
    private List<Gift> acceptGifts = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Integral> getIntegrals() {
        return integrals;
    }

    public void setIntegrals(List<Integral> integrals) {
        this.integrals = integrals;
    }

    public List<Gift> getPublishGifts() {
        return publishGifts;
    }

    public void setPublishGifts(List<Gift> publishGifts) {
        this.publishGifts = publishGifts;
    }

    public List<Gift> getAcceptGifts() {
        return acceptGifts;
    }

    public void setAcceptGifts(List<Gift> acceptGifts) {
        this.acceptGifts = acceptGifts;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", Description='" + Description + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", avatar='" + avatar + '\'' +
                ", integrals=" + integrals +
                ", publishGifts=" + publishGifts +
                ", acceptGifts=" + acceptGifts +
                '}';
    }
}
