package com.loan.stl.network;

import com.loan.stl.common.AppConfig;
import com.loan.stl.network.converter.SIXConverterFactory;
import com.loan.stl.network.interceptor.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/5 10:30
 * <p/>
 * Description: 网络请求client
 */
public class SIXClient {
    // 网络请求超时时间值(s)
    private static final int    DEFAULT_TIMEOUT = 60;
    // retrofit实例
    private Retrofit retrofit;

    /**
     * 私有化构造方法
     */
    private SIXClient() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // 添加签名参数
//        builder.addInterceptor(new BasicParamsInject().getInterceptor());
        // 打印参数
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(true);
//        String inputUrl = (String) SharedInfo.getInstance().getValue("input_url", "");

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.URL_SIX)
                .client(builder.build())
                .addConverterFactory(SIXConverterFactory.create())
                .build();
    }

    /**
     * 调用单例对象
     */
    private static SIXClient getInstance() {
        return RDClientInstance.instance;
    }

    /**
     * 创建单例对象
     */
    private static class RDClientInstance {
        static SIXClient instance = new SIXClient();
    }

    ///////////////////////////////////////////////////////////////////////////
    // service
    ///////////////////////////////////////////////////////////////////////////
    private static TreeMap<String, Object> serviceMap;

    private static TreeMap<String, Object> getServiceMap() {
        if (serviceMap == null)
            serviceMap = new TreeMap<>();
        return serviceMap;
    }

    /**
     * @return 指定service实例
     */
    public static <T> T getService(Class<T> clazz) {
        if (getServiceMap().containsKey(clazz.getSimpleName())) {
            return (T) getServiceMap().get(clazz.getSimpleName());
        }
        T service = SIXClient.getInstance().retrofit.create(clazz);
        getServiceMap().put(clazz.getSimpleName(), service);
        return service;
    }
}
