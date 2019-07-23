package com.loan.stl.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/5 15:23
 * <p/>
 * Description: 网络返回消息，最外层解析实体
 */
@SuppressWarnings("unused")
public class HttpSixResult<T> {
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** 错误码 */
    @SerializedName(Params.RES_STAUTS)
    private int    status;
    /** 错误信息 */
    @SerializedName(Params.RES_MESSAGE)
    private String message;
    /** 消息响应的主体 */
    @SerializedName(Params.RES_DATA)
    private T      data;
//    @SerializedName(Params.RES_PAGE)
    private PageMo page;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PageMo getPage() {
        return page;
    }

    public void setPage(PageMo page) {
        this.page = page;
    }
}
