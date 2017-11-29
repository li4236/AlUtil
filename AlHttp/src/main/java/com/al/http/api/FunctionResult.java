package com.al.http.api;

import android.text.TextUtils;

import com.al.http.bean.AlInfo;
import com.al.http.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @Description: ResponseBody转ApiResult<T>
 */
public class FunctionResult<T> implements Function<ResponseBody, AlInfo<T>> {

    protected Type type;

    public FunctionResult(Type type) {

        this.type = type;
    }

    @Override
    public AlInfo<T> apply(ResponseBody responseBody) throws Exception {

        AlInfo<T> alResponse = new AlInfo<>();

        if (String.class.equals(type)) {//字符串处理

            alResponse.setResult((T) responseBody.string());

            return alResponse;

        } else {

            try {
                String json = responseBody.string();

                AlInfo result = parseApiResult(json, alResponse);

                if (result != null && !TextUtils.isEmpty((CharSequence) result.getResult())) {

                    if (((String) alResponse.getResult()).charAt(0) == '[') {

                        T data = GsonUtil.gson().fromJson(alResponse.getResult().toString(), new TypeToken<T>() {
                        }.getType());
                        alResponse.setResult(data);
                    } else if (((String) alResponse.getResult()).charAt(0) == '{') {

                        T data = GsonUtil.gson().fromJson(alResponse.getResult().toString(), type);
                        alResponse.setResult(data);
                    } else {
                        alResponse.setReason("AlInfo's data is null");
                    }

                } else {
                    alResponse.setReason("json is null");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                alResponse.setReason(e.getMessage());
            } finally {
                responseBody.close();
            }
            return alResponse;
        }
    }

    private AlInfo parseApiResult(String json, AlInfo alResponse) throws JSONException {
        if (TextUtils.isEmpty(json)) return null;
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has("error_code")) {
            alResponse.setCode(jsonObject.getInt("error_code"));
        }
        if (jsonObject.has("result")) {
            alResponse.setResult(jsonObject.getString("result"));
        }
        if (jsonObject.has("reason")) {
            alResponse.setReason(jsonObject.getString("reason"));
        }
        return alResponse;
    }
}
