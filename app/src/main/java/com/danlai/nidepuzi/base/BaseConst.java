package com.danlai.nidepuzi.base;

import android.os.Environment;

/**
 * Created by wulei on 2016/1/22.
 */
public class BaseConst {

    //订单状态
    public static final int ORDER_STATE_CREATE = 0;
    public static final int ORDER_STATE_WAITPAY = 1;
    public static final int ORDER_STATE_PAYED = 2;
    public static final int ORDER_STATE_SENDED = 3;
    public static final int ORDER_STATE_CONFIRM_RECEIVE = 4;
    public static final int ORDER_STATE_TRADE_SUCCESS = 5;
    public static final int ORDER_STATE_REFUND_CLOSE = 6;
    public static final int ORDER_STATE_TRADE_CLOSE = 7;

    //退货退款状态
    public static final int REFUND_STATE_NO_REFUND = 0;
    public static final int REFUND_STATE_BUYER_APPLY = 3;
    public static final int REFUND_STATE_SELLER_AGREED = 4;
    public static final int REFUND_STATE_BUYER_RETURNED_GOODS = 5;
    public static final int REFUND_STATE_SELLER_REJECTED = 2;
    public static final int REFUND_STATE_WAIT_RETURN_FEE = 6;
    public static final int REFUND_STATE_REFUND_CLOSE = 1;
    public static final int REFUND_STATE_REFUND_SUCCESS = 7;

    //优惠券状态
    public static final int UNUSED_COUPON = 0;
    public static final int FREEZE_COUPON = 1;
    public static final int PAST_COUPON = 2;
    public static final int USED_COUPON = 3;
    public static final int DISABLE_COUPON = 4;

    //订单类型
    public static final int ALL_ORDER = 0;
    public static final int WAIT_PAY = 1;
    public static final int WAIT_SEND = 2;

    //小米推送
    public static final String MIPUSH_ID = "2882303761517576471";
    public static final String MIPUSH_KEY = "5861757624471";
    public static final String MIPUSH_SECRET = "N94b6BmlG/MNLDMkKvPTSg==";

    //微信
    public static final String WX_APP_ID = "wxa6e8010fa0b31eb3";
    public static final String WX_APP_SECRET = "a894a72567440fa7317843d76dd7bf03";

    //支付宝
    public static final String ALIPAY_APP_ID = "2017042206893641";

    //成交订单类别
    public static final int ORDER_SHARE = 4;
    public static final int ORDER_SELF = 0;


    public static final String BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()
        + "/nidepuzi/";

    public static final String CATEGORY_JSON = BASE_DIR + "category.json";

    private static final String[] NUMBER = {"零", "一", "二", "三", "四", "五"
        , "六", "七", "八", "九", "十"};

    public static String numberToWord(int num) {
        if (num >= 0 && num <= 10) {
            return NUMBER[num];
        } else if (num <= 99) {
            if (num % 10 == 0) {
                return NUMBER[num / 10] + "十";
            } else {
                return NUMBER[num / 10] + "十" + NUMBER[num % 10];
            }
        } else if (num == 100) {
            return "百";
        } else {
            return "多";
        }
    }

    public static int get_reason_num(String reason) {
        int reason_num = 0;
        switch (reason) {
            case "其他":
                reason_num = 0;
                break;
            case "错拍":
                reason_num = 1;
                break;
            case "缺货":
                reason_num = 2;
                break;
            case "开线/脱色/脱毛/有色差/有虫洞":
                reason_num = 3;
                break;
            case "发错货/漏发":
                reason_num = 4;
                break;
            case "没有发货":
                reason_num = 5;
                break;
            case "未收到货":
                reason_num = 6;
                break;
            case "与描述不符":
                reason_num = 7;
                break;
            case "发票问题":
                reason_num = 9;
                break;
            case "七天无理由退换货":
                reason_num = 10;
                break;
            default:
                break;
        }
        return reason_num;
    }
}
