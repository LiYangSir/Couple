package com.quqiququai.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_gift")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String giftUrl;
    private String task;
    private Integer integral;
    private String state;


    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL)
    private List<Picture> giftPictures = new ArrayList<>();

    private String firstPicUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date auditTime;

    private boolean publishFlag;
    private boolean achieveFlag;
    private boolean auditFlag;

    @ManyToOne
    private Keyword keyword;

    @ManyToOne
    private User publishUser;

    @ManyToOne
    private User acceptUser;

    public Gift() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGiftUrl() {
        return giftUrl;
    }

    public void setGiftUrl(String giftUrl) {
        this.giftUrl = giftUrl;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public List<Picture> getGiftPictures() {
        return giftPictures;
    }

    public void setGiftPictures(List<Picture> giftPictures) {
        this.giftPictures = giftPictures;
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

    public boolean isPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(boolean publishFlag) {
        this.publishFlag = publishFlag;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public boolean isAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(boolean auditFlag) {
        this.auditFlag = auditFlag;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public User getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(User publishUser) {
        this.publishUser = publishUser;
    }

    public User getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(User acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getFirstPicUrl() {
        return firstPicUrl;
    }

    public void setFirstPicUrl(String firstPicUrl) {
        this.firstPicUrl = firstPicUrl;
    }

    public boolean isAchieveFlag() {
        return achieveFlag;
    }

    public void setAchieveFlag(boolean achieveFlag) {
        this.achieveFlag = achieveFlag;
    }


    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", giftUrl='" + giftUrl + '\'' +
                ", task='" + task + '\'' +
                ", integral=" + integral +
                ", state='" + state + '\'' +
                ", giftPictures=" + giftPictures +
                ", firstPicUrl='" + firstPicUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", auditTime=" + auditTime +
                ", publishFlag=" + publishFlag +
                ", achieveFlag=" + achieveFlag +
                ", auditFlag=" + auditFlag +
                ", keyword=" + keyword +
                ", publishUser=" + publishUser +
                ", acceptUser=" + acceptUser +
                '}';
    }
}
