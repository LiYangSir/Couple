package com.quqiququai.vo;

import com.quqiququai.po.Gift;
import com.quqiququai.po.User;

import java.util.List;

public class FooterQuery {

    private List<Gift> giftList;
    private List<User> users;
    private String description;

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FooterQuery{" +
                "giftList=" + giftList +
                ", users=" + users +
                ", description='" + description + '\'' +
                '}';
    }
}
