package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by 优尼世界 on 2016/01/15.
 *
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class CartsPayInfoBean {

  @SerializedName("pay_extras") private List<payExtrasEntityApp> mPayExtras;
  @SerializedName("logistics_companys") private List<LogisticsCompanys>
      mLogisticsCompanyses;
  @SerializedName("wallet_cash") private double mWalletCash;
  @SerializedName("total_payment") private double mTotalPayment;
  @SerializedName("wallet_payable") private boolean mWalletPayable;
  @SerializedName("weixin_payable") private boolean mWeixinPayable;
  @SerializedName("coupon_ticket") private Object mCouponTicket;
  @SerializedName("uuid") private String mUuid;
  @SerializedName("budget_cash") private double budget_cash;
  @SerializedName("budget_payable") private boolean budget_payable;
  @SerializedName("coupon_message") private String mCoupon_message;
  @SerializedName("alipay_payable") private boolean mAlipayPayable;
  @SerializedName("discount_fee") private double mDiscountFee;
  @SerializedName("cart_ids") private String mCartIds;
  @SerializedName("total_fee") private double mTotalFee;
  @SerializedName("post_fee") private double mPostFee;
  @SerializedName("cart_list") private List<CartListEntity> mCartList;
  @SerializedName("max_personalinfo_level")private int maxPersonalInfoLevel;

  public List<LogisticsCompanys> getLogisticsCompanyses() {
    return mLogisticsCompanyses;
  }

  @Override public String toString() {
    return "CartsPayInfoBean{" +
        "mPayExtras=" + mPayExtras +
        ", mWalletCash=" + mWalletCash +
        ", mTotalPayment=" + mTotalPayment +
        ", mWalletPayable=" + mWalletPayable +
        ", mWeixinPayable=" + mWeixinPayable +
        ", mCouponTicket=" + mCouponTicket +
        ", mUuid='" + mUuid + '\'' +
        ", budget_cash='" + budget_cash + '\'' +
        ", budget_payable=" + budget_payable +
        ", mCoupon_message='" + mCoupon_message + '\'' +
        ", mAlipayPayable=" + mAlipayPayable +
        ", mDiscountFee=" + mDiscountFee +
        ", mCartIds='" + mCartIds + '\'' +
        ", mTotalFee=" + mTotalFee +
        ", mPostFee=" + mPostFee +
        ", mCartList=" + mCartList +
        '}';
  }

  public List<payExtrasEntityApp> getmPayExtras() {
    return mPayExtras;
  }

  public void setmPayExtras(List<payExtrasEntityApp> mPayExtras) {
    this.mPayExtras = mPayExtras;
  }

  public boolean isBudget_payable() {
    return budget_payable;
  }

  public void setBudget_payable(boolean budget_payable) {
    this.budget_payable = budget_payable;
  }

  public double getBudget_cash() {
    return budget_cash;
  }

  public void setBudget_cash(double budget_cash) {
    this.budget_cash = budget_cash;
  }

  public String getmCoupon_message() {
    return mCoupon_message;
  }

  public void setmCoupon_message(String mCoupon_message) {
    this.mCoupon_message = mCoupon_message;
  }

  public double getWalletCash() {
    return mWalletCash;
  }

  public void setWalletCash(double walletCash) {
    this.mWalletCash = walletCash;
  }

  public double getTotalPayment() {
    return mTotalPayment;
  }

  public void setTotalPayment(double totalPayment) {
    this.mTotalPayment = totalPayment;
  }

  public boolean isWalletPayable() {
    return mWalletPayable;
  }

  public void setWalletPayable(boolean walletPayable) {
    this.mWalletPayable = walletPayable;
  }

  public boolean isWeixinPayable() {
    return mWeixinPayable;
  }

  public void setWeixinPayable(boolean weixinPayable) {
    this.mWeixinPayable = weixinPayable;
  }

  public Object getCouponTicket() {
    return mCouponTicket;
  }

  public void setCouponTicket(Object couponTicket) {
    this.mCouponTicket = couponTicket;
  }

  public String getUuid() {
    return mUuid;
  }

  public void setUuid(String uuid) {
    this.mUuid = uuid;
  }

  public boolean isAlipayPayable() {
    return mAlipayPayable;
  }

  public void setAlipayPayable(boolean alipayPayable) {
    this.mAlipayPayable = alipayPayable;
  }

  public double getDiscountFee() {
    return mDiscountFee;
  }

  public void setDiscountFee(double discountFee) {
    this.mDiscountFee = discountFee;
  }

  public String getCartIds() {
    return mCartIds;
  }

  public void setCartIds(String cartIds) {
    this.mCartIds = cartIds;
  }

  public double getTotalFee() {
    return mTotalFee;
  }

  public void setTotalFee(double totalFee) {
    this.mTotalFee = totalFee;
  }

  public double getPostFee() {
    return mPostFee;
  }

  public void setPostFee(double postFee) {
    this.mPostFee = postFee;
  }

  public List<CartListEntity> getCartList() {
    return mCartList;
  }

  public void setCartList(List<CartListEntity> cartList) {
    this.mCartList = cartList;
  }

  public int getMaxPersonalInfoLevel() {
    return maxPersonalInfoLevel;
  }

  public void setMaxPersonalInfoLevel(int maxPersonalInfoLevel) {
    this.maxPersonalInfoLevel = maxPersonalInfoLevel;
  }

  public static class CartListEntity {
    @SerializedName("id") private int mId;
    @SerializedName("url") private String mUrl;
    @SerializedName("buyer_id") private int mBuyerId;
    @SerializedName("buyer_nick") private String mBuyerNick;
    @SerializedName("item_id") private String mItemId;
    @SerializedName("title") private String mTitle;
    @SerializedName("price") private double mPrice;
    @SerializedName("std_sale_price") private double mStdSalePrice;
    @SerializedName("sku_id") private String mSkuId;
    @SerializedName("num") private int mNum;
    @SerializedName("total_fee") private double mTotalFee;
    @SerializedName("sku_name") private String mSkuName;
    @SerializedName("pic_path") private String mPicPath;
    @SerializedName("created") private String mCreated;
    @SerializedName("status") private int mStatus;
    @SerializedName("is_bonded_goods") private boolean is_bonded_goods;
    @SerializedName("type") private int type;
    @SerializedName("product_type")private int product_type;

    @Override public String toString() {
      return "CartListEntity{" +
          "mId=" + mId +
          ", mUrl='" + mUrl + '\'' +
          ", mBuyerId=" + mBuyerId +
          ", mBuyerNick='" + mBuyerNick + '\'' +
          ", mItemId='" + mItemId + '\'' +
          ", mTitle='" + mTitle + '\'' +
          ", mPrice=" + mPrice +
          ", mStdSalePrice=" + mStdSalePrice +
          ", mSkuId='" + mSkuId + '\'' +
          ", mNum=" + mNum +
          ", mTotalFee=" + mTotalFee +
          ", mSkuName='" + mSkuName + '\'' +
          ", mPicPath='" + mPicPath + '\'' +
          ", mCreated='" + mCreated + '\'' +
          ", mStatus=" + mStatus +
          '}';
    }

    public int getmId() {
      return mId;
    }

    public void setmId(int mId) {
      this.mId = mId;
    }

    public String getmUrl() {
      return mUrl;
    }

    public void setmUrl(String mUrl) {
      this.mUrl = mUrl;
    }

    public int getmBuyerId() {
      return mBuyerId;
    }

    public void setmBuyerId(int mBuyerId) {
      this.mBuyerId = mBuyerId;
    }

    public String getmBuyerNick() {
      return mBuyerNick;
    }

    public void setmBuyerNick(String mBuyerNick) {
      this.mBuyerNick = mBuyerNick;
    }

    public String getmItemId() {
      return mItemId;
    }

    public void setmItemId(String mItemId) {
      this.mItemId = mItemId;
    }

    public String getmTitle() {
      return mTitle;
    }

    public void setmTitle(String mTitle) {
      this.mTitle = mTitle;
    }

    public double getmPrice() {
      return mPrice;
    }

    public void setmPrice(double mPrice) {
      this.mPrice = mPrice;
    }

    public double getmStdSalePrice() {
      return mStdSalePrice;
    }

    public void setmStdSalePrice(double mStdSalePrice) {
      this.mStdSalePrice = mStdSalePrice;
    }

    public String getmSkuId() {
      return mSkuId;
    }

    public void setmSkuId(String mSkuId) {
      this.mSkuId = mSkuId;
    }

    public int getmNum() {
      return mNum;
    }

    public void setmNum(int mNum) {
      this.mNum = mNum;
    }

    public double getmTotalFee() {
      return mTotalFee;
    }

    public void setmTotalFee(double mTotalFee) {
      this.mTotalFee = mTotalFee;
    }

    public String getmSkuName() {
      return mSkuName;
    }

    public void setmSkuName(String mSkuName) {
      this.mSkuName = mSkuName;
    }

    public String getmPicPath() {
      return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
      this.mPicPath = mPicPath;
    }

    public String getmCreated() {
      return mCreated;
    }

    public void setmCreated(String mCreated) {
      this.mCreated = mCreated;
    }

    public int getmStatus() {
      return mStatus;
    }

    public void setmStatus(int mStatus) {
      this.mStatus = mStatus;
    }

    public int getId() {
      return mId;
    }

    public void setId(int id) {
      this.mId = id;
    }

    public String getUrl() {
      return mUrl;
    }

    public void setUrl(String url) {
      this.mUrl = url;
    }

    public int getBuyerId() {
      return mBuyerId;
    }

    public void setBuyerId(int buyerId) {
      this.mBuyerId = buyerId;
    }

    public String getBuyerNick() {
      return mBuyerNick;
    }

    public void setBuyerNick(String buyerNick) {
      this.mBuyerNick = buyerNick;
    }

    public String getItemId() {
      return mItemId;
    }

    public void setItemId(String itemId) {
      this.mItemId = itemId;
    }

    public String getTitle() {
      return mTitle;
    }

    public void setTitle(String title) {
      this.mTitle = title;
    }

    public double getPrice() {
      return mPrice;
    }

    public void setPrice(double price) {
      this.mPrice = price;
    }

    public double getStdSalePrice() {
      return mStdSalePrice;
    }

    public void setStdSalePrice(double stdSalePrice) {
      this.mStdSalePrice = stdSalePrice;
    }

    public String getSkuId() {
      return mSkuId;
    }

    public void setSkuId(String skuId) {
      this.mSkuId = skuId;
    }

    public int getNum() {
      return mNum;
    }

    public void setNum(int num) {
      this.mNum = num;
    }

    public double getTotalFee() {
      return mTotalFee;
    }

    public void setTotalFee(double totalFee) {
      this.mTotalFee = totalFee;
    }

    public String getSkuName() {
      return mSkuName;
    }

    public void setSkuName(String skuName) {
      this.mSkuName = skuName;
    }

    public String getPicPath() {
      return mPicPath;
    }

    public void setPicPath(String picPath) {
      this.mPicPath = picPath;
    }

    public String getCreated() {
      return mCreated;
    }

    public void setCreated(String created) {
      this.mCreated = created;
    }

    public int getStatus() {
      return mStatus;
    }

    public void setStatus(int status) {
      this.mStatus = status;
    }

    public boolean is_bonded_goods() {
      return is_bonded_goods;
    }

    public void setIs_bonded_goods(boolean is_bonded_goods) {
      this.is_bonded_goods = is_bonded_goods;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public int getProduct_type() {
      return product_type;
    }

    public void setProduct_type(int product_type) {
      this.product_type = product_type;
    }
  }

  public static class LogisticsCompanys {

    /**
     * is_priority : true
     * code :
     * id :
     * name : 自动分配
     */

    @SerializedName("is_priority") private boolean mIsPriority;
    @SerializedName("code") private String mCode;
    @SerializedName("id") private String mId;
    @SerializedName("name") private String mName;

    public boolean isIsPriority() {
      return mIsPriority;
    }

    public void setIsPriority(boolean isPriority) {
      mIsPriority = isPriority;
    }

    public String getCode() {
      return mCode;
    }

    public void setCode(String code) {
      mCode = code;
    }

    public String getId() {
      return mId;
    }

    public void setId(String id) {
      mId = id;
    }

    public String getName() {
      return mName;
    }

    public void setName(String name) {
      mName = name;
    }
  }

  public static class payExtrasEntityApp {

    @SerializedName("type") private int mType;
    @SerializedName("pid") private int mPid;
    @SerializedName("name") private String mName;
    @SerializedName("use_coupon_allowed") private int mUseCouponAllowed;
    @SerializedName("value") private double mValue;

    @Override public String toString() {
      return "payExtrasEntityApp{" +
          "mType=" + mType +
          ", mPid=" + mPid +
          ", mName='" + mName + '\'' +
          ", mUseCouponAllowed=" + mUseCouponAllowed +
          ", mValue=" + mValue +
          '}';
    }

    public int getUseCouponAllowed() {
      return mUseCouponAllowed;
    }

    public void setUseCouponAllowed(int mUseCouponAllowed) {
      this.mUseCouponAllowed = mUseCouponAllowed;
    }

    public int getType() {
      return mType;
    }

    public void setType(int type) {
      mType = type;
    }

    public int getPid() {
      return mPid;
    }

    public void setPid(int pid) {
      mPid = pid;
    }

    public String getName() {
      return mName;
    }

    public void setName(String name) {
      mName = name;
    }

    public double getValue() {
      return mValue;
    }

    public void setValue(double value) {
      mValue = value;
    }
  }
}
