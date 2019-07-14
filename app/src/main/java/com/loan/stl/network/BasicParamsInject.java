package com.loan.stl.network;

import com.loan.stl.LoanApplication;
import com.loan.stl.common.BaseParams;
import com.loan.stl.common.Constant;
import com.loan.stl.network.interceptor.BasicParamsInterceptor;
import com.loan.stl.network.interceptor.IBasicDynamic;
import com.loan.stl.utils.device.DeviceInfoUtils;
import okhttp3.Interceptor;

import java.util.Map;
import java.util.TreeMap;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/5 17:59
 * <p/>
 * Description: 拦截器 - 用于添加签名参数
 */
class BasicParamsInject {
    private BasicParamsInterceptor interceptor;

    BasicParamsInject() {
        // 设置静态参数
        interceptor = new BasicParamsInterceptor.Builder()
//                .addBodyParam(Constant.SIGNA, MDUtil.encode(MDUtil.TYPE.MD5,AppConfig.APP_KEY+"+"+AppConfig.APP_SECRET))
//                .addBodyParam(Constant.APP_KEY,AppConfig.APP_KEY)
                .addBodyParam(Constant.MOBILE_TYPE, BaseParams.MOBILE_TYPE)
//                .addBodyParam(Constant.VERSION_NUMBER,  "1.0.1-debug")
                .addBodyParam(Constant.VERSION_NUMBER,DeviceInfoUtils.getVersionName(LoanApplication.getContext()))
                .build();
        // 设置动态参数
        interceptor.setIBasicDynamic(new IBasicDynamic() {
            @Override
            public String signParams(String postBodyString) {
                //post提交动态添加参数
                return UrlUtils.getInstance().dynamicParams(postBodyString);
            }

            @Override
            public Map signParams(Map map) {
                //get提交动态添加参数
                TreeMap temp = new TreeMap(map);
                return UrlUtils.getInstance().dynamicParams(temp);
            }

            @Override
            public Map signHeadParams(Map headMap) {
                return UrlUtils.getInstance().signParams(headMap);
            }
        });
    }
    Interceptor getInterceptor() {
        return interceptor;
    }
}