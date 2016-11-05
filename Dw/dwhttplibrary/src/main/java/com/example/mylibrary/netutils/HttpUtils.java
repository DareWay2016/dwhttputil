package com.example.mylibrary.netutils;

import android.util.Log;

import com.example.mylibrary.dwutils.StorageUtil;
import com.example.mylibrary.netutils.okhttp.HttpCallBack;
import com.example.mylibrary.netutils.okhttp.OkClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/25.
 */

public class HttpUtils {
    private static String TAG=HttpUtils.class.getSimpleName();
    /**
     * 创建一个http请求
     *
     * @return
     */
    private static ArrayList<Call> calls;

    public static Call doPost(String url, HashMap<String, String> params, HttpCallBack<String> callBack) {
        if (calls == null) {
            calls = new ArrayList<>();
        }
        OkClient client = NetUtils.getOkHttpClient()
                .connectTimeOut(30).//设置连接超时
                init();
        client.getCalls(calls);
        return client.doHttpPost(url, params, callBack);

    }

    /**
     * 取消页面中的网络请求
     */
    public static void cancelRequest() {
        Log.d(TAG, "cancelRequest: ");
        if (calls != null) {
            Log.d(TAG, "cancelRequest: "+calls.size());
            for (Call c :
                    calls) {
                if (c != null) {
                    Log.d(TAG, "cancelRequest: "+c.isCanceled());
                    c.cancel();
                    Log.d(TAG, "cancelRequest: after"+c.isCanceled());
                }

            }
        }
    }

    /**
     * 初始化 https客户端，用来访问https的url
     *
     * @return
     */
    public static Call doHttpsPost(String cerpath, String url, HashMap<String, String> params, HttpCallBack<String> callBack) {

        InputStream certInputStream = null;
        try {
            certInputStream = new FileInputStream(new File(StorageUtil.getSdcardPath() + cerpath));//证书路径

            //    certInputStream =  getAssets().open("bemmc.cer");//assert文件下的证书信息

        } catch (IOException exception) {

        }
        if (calls == null) {
            calls = new ArrayList<>();
        }
        OkClient httpsClient = NetUtils.getOkHttpsClient()
                .connectTimeOut(30)
                .setCertInputStream(certInputStream)
                .init();
        httpsClient.getCalls(calls);
        return httpsClient.doHttpPost(url, params, callBack);
    }
}
