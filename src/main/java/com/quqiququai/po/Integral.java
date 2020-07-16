package com.quqiququai.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_integral")
public class Integral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer currentIntegral;
    private boolean addOrDecFlag;
    private Integer addOrDecNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private User user;

    public Integral() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(Integer currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    public boolean isAddOrDecFlag() {
        return addOrDecFlag;
    }

    public void setAddOrDecFlag(boolean addOrDecFlag) {
        this.addOrDecFlag = addOrDecFlag;
    }

    public Integer getAddOrDecNumber() {
        return addOrDecNumber;
    }

    public void setAddOrDecNumber(Integer addOrDecNumber) {
        this.addOrDecNumber = addOrDecNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Integral{" +
                "id=" + id +
                ", currentIntegral=" + currentIntegral +
                ", addOrDecFlag=" + addOrDecFlag +
                ", addOrDecNumber=" + addOrDecNumber +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}
