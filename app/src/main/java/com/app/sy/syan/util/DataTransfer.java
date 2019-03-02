package com.app.sy.syan.util;


import com.app.sy.syan.data.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class DataTransfer {
    private static DataTransfer dataTransfer = new DataTransfer();

    public static DataTransfer getInstance() {
        return dataTransfer;
    }

    public List<GoodsInfo> cartGoods = new ArrayList<>();
}
