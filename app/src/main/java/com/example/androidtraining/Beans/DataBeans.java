package com.example.androidtraining.Beans;

import java.util.List;

/**
 * Created by 黄铿 on 2019/1/6.
 */

public class DataBeans {
    private String status;
    private List<ChatBeans> chatBeans;
    private List<UserBeans> userBeans;
    private LoginBeans loginBeans;

    public List<ChatBeans> getChatBeans() {
        return chatBeans;
    }

    public void setChatBeans(List<ChatBeans> chatBeans) {
        this.chatBeans = chatBeans;
    }

    public List<UserBeans> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(List<UserBeans> userBeans) {
        this.userBeans = userBeans;
    }

    public LoginBeans getLoginBeans() {
        return loginBeans;
    }

    public void setLoginBeans(LoginBeans loginBeans) {
        this.loginBeans = loginBeans;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
