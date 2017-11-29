package com.al.test.other;

import org.al.common.bean.AlBannerInfo;

/**
 * Created by li4236 on 2017/11/1.
 */

public class TestInfo extends AlBannerInfo {

    private String type;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
