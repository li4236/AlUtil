/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.al.http.impl;

import com.al.http.bean.AlInfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.observers.DisposableObserver;

/**
 * <p>描述：获取类型接口</p>
 * li4236
 */
public abstract class IType<T> extends DisposableObserver<AlInfo<T>> {
    /**
     * 获得泛型的class的方法
     * @return
     */
    private Type getTypeClass(){

        Type type =  this.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType){
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        }else {
            return null;
        }

    }

    public Type getType() {
        return getTypeClass();
    }

}
