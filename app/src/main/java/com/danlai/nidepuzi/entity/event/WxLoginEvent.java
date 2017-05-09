package com.danlai.nidepuzi.entity.event;

/**
 * @author wisdom
 * @date 2017年05月08日 下午3:56
 */

public class WxLoginEvent {
    private String code;
    private int errCode;

    public WxLoginEvent(String code, int errCode) {
        this.code = code;
        this.errCode = errCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
