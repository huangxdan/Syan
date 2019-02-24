package com.app.sy.syan.data.request;

public class ModifyPwdBody {
    private String loginName;
    private String oldloginPwd;
    private String newloginPwd;

    public ModifyPwdBody(String loginName, String oldloginPwd, String newloginPwd) {
        this.loginName = loginName;
        this.oldloginPwd = oldloginPwd;
        this.newloginPwd = newloginPwd;
    }
}
