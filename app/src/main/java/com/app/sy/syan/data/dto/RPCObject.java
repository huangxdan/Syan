package com.app.sy.syan.data.dto;

/**
 * 服务器返回的列表
 */
public class RPCObject<T> extends RPCMessage {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
