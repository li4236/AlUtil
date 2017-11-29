package com.al.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;
import java.text.NumberFormat;


public class DownLoadProgress implements Parcelable {
    private long totalSize;
    private long downloadSize;


    public DownLoadProgress() {
    }

    public DownLoadProgress(long totalSize, long downloadSize) {
        this.totalSize = totalSize;
        this.downloadSize = downloadSize;
    }

    protected DownLoadProgress(Parcel in) {
        totalSize = in.readLong();
        downloadSize = in.readLong();
    }

    public long getTotalSize() {
        return totalSize;
    }

    public DownLoadProgress setTotalSize(long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public long getDownloadSize() {
        return downloadSize;
    }

    public DownLoadProgress setDownloadSize(long downloadSize) {
        this.downloadSize = downloadSize;
        return this;
    }

    @Override
    public String toString() {
        return "DownLoadProgress{" +
                "downloadSize=" + downloadSize +
                ", totalSize=" + totalSize +
                '}';
    }

    /**
     * 是否下载完成
     *
     * @return
     */
    public boolean isDownComplete() {
        return downloadSize == totalSize;
    }

    /**
     * 获得格式化的总Size
     *
     * @return example: 2KB , 10MB
     */
    public String getFormatTotalSize() {
        return byteCountToDisplaySize(totalSize);
    }

    /**
     * 获得格式化的当前大小
     *
     * @return
     */
    public String getFormatDownloadSize() {
        return byteCountToDisplaySize(downloadSize);
    }

    /**
     * 获得格式化的状态字符串
     *
     * @return example: 2MB/36MB
     */
    public String getFormatStatusString() {
        return getFormatDownloadSize() + "/" + getFormatTotalSize();
    }

    /**
     * 数字精度条
     */
    public int getProgressBar() {
        return (int) (getDownloadSize() * 100 / getTotalSize());
    }


    /**
     * 获得下载的百分比, 保留两位小数
     *
     * @return example: 5.25%
     */
    public String getPercent() {
        String percent;
        Double result;
        if (totalSize == 0L) {
            result = 0.0;
        } else {
            result = downloadSize * 1.0 / totalSize;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        //控制保留小数点后几位，2：表示保留2位小数点
        nf.setMinimumFractionDigits(2);
        percent = nf.format(result);
        return percent;
    }

    public static final Creator<DownLoadProgress> CREATOR = new Creator<DownLoadProgress>() {
        @Override
        public DownLoadProgress createFromParcel(Parcel in) {
            return new DownLoadProgress(in);
        }

        @Override
        public DownLoadProgress[] newArray(int size) {
            return new DownLoadProgress[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(totalSize);
        parcel.writeLong(downloadSize);
    }

    public static final long ONE_KB = 1024;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);
    public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);

    public static String byteCountToDisplaySize(BigInteger size) {
        String displaySize;

        if (size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_EB_BI)) + " EB";
        } else if (size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_PB_BI)) + " PB";
        } else if (size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_TB_BI)) + " TB";
        } else if (size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_GB_BI)) + " GB";
        } else if (size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_MB_BI)) + " MB";
        } else if (size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
            displaySize = String.valueOf(size.divide(ONE_KB_BI)) + " KB";
        } else {
            displaySize = String.valueOf(size) + " bytes";
        }
        return displaySize;
    }

    public static String byteCountToDisplaySize(long size) {
        return byteCountToDisplaySize(BigInteger.valueOf(size));
    }
}
