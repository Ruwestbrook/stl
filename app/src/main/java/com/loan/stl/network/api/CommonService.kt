package com.loan.stl.network.api

import com.loan.stl.module.mine.dataModel.CommonRec
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.ListData
import retrofit2.Call
import retrofit2.http.GET

/**
author: russell
time: 2019-07-14:14:16
describe：
 */
interface CommonService {
    /** 协议清单  */
    @GET("protocol/list.htm")
    fun protocolList(): Call<HttpResult<ListData<CommonRec>>>

    /** H5列表  */
    @GET("h5/list.htm")
    fun h5List(): Call<HttpResult<ListData<CommonRec>>>

    /** 备注清单  */
    @GET("remark/list.htm")
    fun remarkList(): Call<HttpResult<ListData<CommonRec>>>
}