package com.app.sy.syan.data.dto;

import java.util.List;


public class RPCDataList<T> extends RPCMessage{
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
