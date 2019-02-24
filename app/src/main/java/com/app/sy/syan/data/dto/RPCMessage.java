package com.app.sy.syan.data.dto;

public class RPCMessage implements BaseInfo {
    public String msg = null;
    public int code = -1;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        if (code == 200 || code == 1000) {
            return true;
        }
        return false;
    }
}
