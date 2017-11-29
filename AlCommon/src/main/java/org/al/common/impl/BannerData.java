package org.al.common.impl;

import org.al.common.adapter.AlAdverOnClickListener;

import java.util.List;

/**
 * Created by li4236 on 2017/11/2.
 */

public interface BannerData<T> {


    public T addCircleDatas(List<T> datas, AlAdverOnClickListener callback, AlImageLoader alImageLoader);

    public T addNoCircleDatas(List<T> datas, AlAdverOnClickListener callback, AlImageLoader alImageLoader);
}
