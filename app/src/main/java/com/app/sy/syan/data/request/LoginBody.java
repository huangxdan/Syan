package com.app.sy.syan.data.request;

public class LoginBody {
    private String loginName;
    private String loginPwd;

    public LoginBody(String loginName, String loginPwd) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }
}
