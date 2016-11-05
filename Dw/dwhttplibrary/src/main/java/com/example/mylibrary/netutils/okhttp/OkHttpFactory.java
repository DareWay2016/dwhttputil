package com.example.mylibrary.netutils.okhttp;

import com.example.mylibrary.netutils.factory.NetHttpClient;
import com.example.mylibrary.netutils.factory.HttpFactory;

/**
 * Created by myn on 2016/10/17.
 */

public class OkHttpFactory implements HttpFactory {

    private NetHttpClient client;

    @Override
    public NetHttpClient  createHttpClient() {

        return new OkClient();
    }

    @Override
    public NetHttpClient createHttpsClient() {
        return new OkHttpsClient();
    }


}
