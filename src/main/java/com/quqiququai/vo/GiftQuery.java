package com.quqiququai.vo;

public class GiftQuery {

    private String task;
    private String keyword;
    private boolean published;
    private Long userId;

    public GiftQuery() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GIftQuery{" +
                "task='" + task + '\'' +
                ", keyword='" + keyword + '\'' +
                ", published=" + published +
                ", userId=" + userId +
                '}';
    }
}
