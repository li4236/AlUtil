package org.al.common.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by li4236 on 16/2/22.
 */
public class ComPhoneUtil {

    /**
     * 手机的IMEI
     */
    public static String getIMEI(Context mContext) {

        TelephonyManager mTelephonyMgr = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);

        String IMEI = mTelephonyMgr.getDeviceId();

        ComLog.E("Imei+"+IMEI);

        return "yidont/" + IMEI;

    }

}
