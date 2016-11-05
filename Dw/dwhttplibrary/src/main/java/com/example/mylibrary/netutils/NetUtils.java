package com.example.mylibrary.netutils;


import com.example.mylibrary.netutils.factory.HttpFactory;
import com.example.mylibrary.netutils.okhttp.OkClient;
import com.example.mylibrary.netutils.okhttp.OkHttpFactory;
import com.example.mylibrary.netutils.okhttp.OkHttpsClient;

/**
 * Created by myn on 2016/10/14.
 */

public class NetUtils {

    public static boolean DEBUG = true;
    private static OkHttpFactory okHttpFactory; //使用OkHttp实现的请求客户端工厂类

    /**
     * 获取 http请求客户端
     * @return
     */
    public static OkClient getOkHttpClient(){

      return  (OkClient) getOkHttpFactory().createHttpClient();

    }

    /**
     * 获取 https 请求客户端
     * @return
     */
    public static OkHttpsClient getOkHttpsClient(){

        return (OkHttpsClient) getOkHttpFactory().createHttpsClient();
    }


    private static HttpFactory getOkHttpFactory(){

        if(okHttpFactory == null){

            okHttpFactory = new OkHttpFactory();
            return okHttpFactory;
        }else {
            return okHttpFactory;
        }

    }

}
