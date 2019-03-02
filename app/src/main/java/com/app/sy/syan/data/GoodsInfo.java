package com.app.sy.syan.data;

public class GoodsInfo {
    //                 --商品id
    private String productId;
    //            		--商品名称
    private String productName;
    private String productImg;
    //      		--商品简介
    private String productDescription;
    //             		--商品信息
    private String productInfo;
    private String chengfen;
    private String tedian;
    private String yongfa;

    //           		--商品价格
    private double productPrice;
    //          		--库存数量
    private String productNumber;


    //购物车列表字段，单个商品加入购物车的数量
    private int goodscount;
    private boolean isSelect;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getChengfen() {
        return chengfen;
    }

    public void setChengfen(String chengfen) {
        this.chengfen = chengfen;
    }

    public String getTedian() {
        return tedian;
    }

    public void setTedian(String tedian) {
        this.tedian = tedian;
    }

    public String getYongfa() {
        return yongfa;
    }

    public void setYongfa(String yongfa) {
        this.yongfa = yongfa;
    }

    public int getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(int goodscount) {
        this.goodscount = goodscount;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
