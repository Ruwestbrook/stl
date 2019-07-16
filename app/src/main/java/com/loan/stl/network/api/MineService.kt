package com.loan.stl.network.api

import com.loan.stl.common.BundleKeys
import com.loan.stl.module.mine.dataModel.receive.*
import com.loan.stl.module.mine.dataModel.submit.CreditLinkerSub
import com.loan.stl.module.mine.dataModel.submit.PhoneInfoSub
import com.loan.stl.module.user.dataModel.receive.InfoRec
import com.loan.stl.module.user.dataModel.receive.PassRec
import com.loan.stl.module.user.dataModel.receive.TradeStateRec
import com.loan.stl.module.user.dataModel.submit.UpdatePwdSub
import com.loan.stl.network.entity.HttpResult
import retrofit2.Call
import retrofit2.http.*

/**
author: russell
time: 2019-07-14:14:01
describe：
 */
interface MineService {
    /** 获取认证信息  */
    @GET("act/mine/userAuth/getUserAuth.htm")
    fun getUserAuth(): Call<HttpResult<CreditStatusRec>>

    /** 获取字典  */
    @GET("act/dict/list.htm")
    abstract fun getDicts(@Query(BundleKeys.TYPE) type: String): Call<HttpResult<DicRec>>

    /** 保存联系人信息  */
    @POST("act/mine/contact/saveOrUpdate.htm")
    fun contactSaveOrUpdate(@Body sub: CreditLinkerSub): Call<HttpResult<Any>>

//    /** 获取联系人信息  */
//    @GET("act/mine/contact/getContactInfoList.htm")
//    abstract fun getContactInfoList(): Call<HttpResult<ListData<CreditLinkerRec>>>

    /** 获取银行卡列表  */
    @GET("act/mine/bankCard/getBankCardList.htm")
    fun getBankCardList(): Call<HttpResult<CreditBankRec>>
//
//    /** 保存银行卡  */
//    @POST("act/mine/bankCard/authSign.htm")
//    abstract fun bankSaveOrUpdate(@Body sub: CreditBankSub): Call<HttpResult<BankRec>>
//
//    /** 保存银行卡  */
//    @POST("act/mine/bankCard/auth.htm")
//    abstract fun bankCardAuth(@Body sub: CreditBankSub): Call<HttpResult<BankRec>>
//
//    /** 银行卡签约处理  */
//    @POST("act/mine/bankCard/authSignReturn.htm")
//    abstract fun authSignReturn(@Body sub: AuthSignSub): Call<HttpResult>
//
//    @GET("act/mine/bankCard/getCaptcha.htm")
//    abstract fun getCaptcha(): Call<HttpResult>
//
//    /** 提交ocr文件流  */
//    @Multipart
//    @POST("act/mine/userInfo/apiLinkfaceIDOcrRequest.htm")
//    abstract fun apiLinkfaceIDOcrRequest(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult<OcrMsgRec>>
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/apiLinkfaceliRequest.htm")
//    abstract fun apiLinkfaceliRequest(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/apiLinkfaceliRequest.htm")
//    abstract fun updateLinkfaceliRequest(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/idCardCredit.htm")
//    abstract fun idCardCredit(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/idCardCredit.htm")
//    abstract fun updateIdCardCredit(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/authentication.htm")
//    abstract fun idCardCreditTwo(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//
//    /*
//         个人信息认证提交: 七牛云方式 不通过文件上传,通过key上传
//     */
//    @POST("act/mine/userInfo/v2/authentication.htm")
//    abstract fun idCardCreditTwoV2(@Body sub: CreditPersonSubTwo): Call<HttpResult>
//
//    /*
//        获取七牛云上传的token
//     */
//
//    @POST("common/auth/qntoken.htm")
//    abstract fun getToken(@HeaderMap head: Map<String, String>): Call<HttpResult<UploadRec>>
//
//
//    /** 个人信息认证提交  */
//    @Multipart
//    @POST("act/mine/userInfo/authentication.htm")
//    abstract fun updateIdCardCreditTwo(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>



    /** 获取个人信息  */
    @GET("act/mine/userInfo/v2/getUserInfo.htm")
    fun getUserInfo(): Call<HttpResult<CreditPersonRec>>

//    /** 运营商信息认证接口  */
//    @POST("act/mine/operator/operatorCollect.htm")
//    abstract fun operatorCollect(@Body sub: CreditPhoneTwoSub): Call<HttpResult<CreditPhoneRec>>
//
//    /** 运营商信息认证接口  */
//    @POST("/api/act/mine/gxb/operator/operatorCredit.htm")
//    abstract fun operatorCredit(@Body sub: CreditGXBSub): Call<HttpResult<CreditGXBRec>>
//
//    /** 芝麻信息  */
//    @GET("act/mine/zhima/view.htm")
//    abstract fun zmxyView(): Call<HttpResult<CreditZmxyRec>>
//
//    /** 工作信息保存  */
//    @POST("act/mine/workInfo/saveOrUpdate.htm")
//    abstract fun workInfoSaveOrUpdate(@Body sub: CreditWorkSub): Call<HttpResult>
//
//    /** OCR使用记录同步  */
//    @POST("act/mine/sdk/synchron.htm")
//    abstract fun ocrSynchron(@Body sub: IdCardSyncSub): Call<HttpResult>

    /** 身份证识别次数初始化  */
    @GET("act/mine/sdk/find.htm")
    fun idCardCreditTime(): Call<HttpResult<IdCardTimeRec>>

//    /** 身份证识别次数初始化  */
//    @GET("act/mine/userInfo/ocrUrl.htm")
//    abstract fun ocrUrl(): Call<HttpResult<FaceOcrRec>>
//
//    /** 工作信息获取  */
//    @GET("act/mine/userInfo/getWorkInfo.htm")
//    abstract fun getWorkInfo(): Call<HttpResult<CreditWorkInfoRec>>
//
//    /** 工作照保存  */
//    @Multipart
//    @POST("act/mine/workInfo/workImgSave.htm")
//    abstract fun workImgSave(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult>
//
//
//    /** 工作照保存  七牛云方式  */
//    @POST("act/mine/workInfo/v2/workImgSave.htm")
//    abstract fun workImgSaveV2(@Body sub: CreditWorkPhotoSub): Call<HttpResult>
//
//
//    /** 上传身份证正面照 获取ocr信息  */
//    @Multipart
//    @POST("act/mine/faceID/getOCR.htm")
//    abstract fun subOcr(@HeaderMap head: Map<String, String>, @PartMap params: Map<String, RequestBody>): Call<HttpResult<RegardOcrMsgRec>>
//
//    /** 上传身份证正面照 获取ocr信息 小视科技 */
//    @Multipart
//    @POST
//    abstract fun subRegardOcr(@Url url: String, @PartMap params: Map<String, RequestBody>): Call<HttpResult<RegardOcrMsgRec>>
//
//    /*
//      2019/07/08 russell 修改
//        工作照查询采用七牛云方式
//        接口地址：act/mine/userInfo/getWorkImg.htm 改为 act/mine/userInfo/v2/getWorkImg.htm
//     */
//
//    /** 工作照查询  */
//    @GET("act/mine/userInfo/v2/getWorkImg.htm")
//    abstract fun getWorkImg(): Call<HttpResult<ListData<String>>>
//
//
//    /** 查询更多  */
//    @GET("act/mine/other/findDetail.htm")
//    abstract fun otherFindDetail(): Call<HttpResult<CreditMoreRec>>
//
//    /** 更多信息  */
//    @POST("act/mine/other/saveOrUpdate.htm")
//    abstract fun otherSaveOrUpdate(@Body sub: CreditMoreSub): Call<HttpResult>
//
//    /** 芝麻信息  */
//    @GET("act/mine/zhima/authorize.htm")
//    abstract fun authorize(): Call<HttpResult<CreditUrlRec>>

    /** 获取个人信息  */
    @POST("act/user/info.htm")
    fun getInfo(): Call<HttpResult<InfoRec>>

    /** 交易密码状态  */
    @POST("act/user/getTradeState.htm")
    fun getTradeState(): Call<HttpResult<TradeStateRec>>

    /** 退出登录  */
    @POST("user/logout.htm")
    fun logout(): Call<HttpResult<Any>>

    /** 修改密码  */
    @POST("act/user/changeLoginPwd.htm")
    fun updatePwd(@Body sub: UpdatePwdSub): Call<HttpResult<Any>>

//    /** 找回交易密码身份验证  */
//    @POST("act/user/validateUser.htm")
//    abstract fun validateUser(@Body sub: ForgotPaySub): Call<HttpResult<PassRec>>

    /** 修改交易密码  */
    @POST("act/user/changeTradePwd.htm")
    fun updatePayPwd(@Body sub: UpdatePwdSub): Call<HttpResult<Any>>

    /** 验证交易密码是否正确  */
    @POST("act/user/validateTradePwd.htm")
    fun validateTradePwd(@Body sub: UpdatePwdSub): Call<HttpResult<PassRec>>

    /** 设置交易密码  */
    @POST("act/user/setTradePwd.htm")
    fun setTradePwd(@Body sub: UpdatePwdSub): Call<HttpResult<Any>>

    /** 重置交易密码  */
    @POST("act/user/resetTradePwd.htm")
    fun resetTradePwd(@Body sub: UpdatePwdSub): Call<HttpResult<Any>>

//    //******************* 代理商及邀请  *********************//
//
//    /** 获取运营商连接  */
//    @GET("act/mine/operator/operatorCredit.htm")
//    abstract fun operatorCredit(): Call<HttpResult<CreditUrlRec>>
//
//    /** 获取公积金连接  */
//    @GET("act/mine/userAuth/accFundRequest.htm")
//    abstract fun accFundRequest(): Call<HttpResult<CreditUrlRec>>
//
//    /** 获取手机号  */
//    @POST("userInvite/findPhone.htm")
//    abstract fun findPhone(): Call<HttpResult<MineInviteRec>>
//
//    /** 我的奖金  */
//    @POST("act/mine/profitAmount/find.htm")
//    abstract fun findBonus(): Call<HttpResult<InviteBonusRec>>
//
//    /** 邀请记录  */
//    @FormUrlEncoded
//    @POST("act/user/inviteList.htm")
//    abstract fun inviteList(@Field("invitId") id: String, @Field("pageNo") current: Int, @Field("pageSize") pageSize: Int): Call<HttpResult<ListData<InviteRecordItemRec>>>
//
//    /** 邀请地址  */
//    @POST("userInvite/findInvite.htm")
//    abstract fun findInvite(): Call<HttpResult<ShareLinkRec>>
//
//    /** 獲取分享圖片  */
//    @POST
//    @Streaming
//    abstract fun readImg(@Url url: String): Call<ResponseBody>
//
//    /**
//     * 奖励明细
//     */
//    @POST("act/mine/profitLog/page.htm")
//    abstract fun profitLog(@Body pageMo: PageMo): Call<HttpResult<ListData<InviteAwardItemRec>>>
//
//    /**
//     * 提现记录
//     */
//    @POST("act/mine/profitCashLog/page.htm")
//    abstract fun profitCashLog(@Body pageMo: PageMo): Call<HttpResult<ListData<InviteWithdrawItemRec>>>
//
//    /** 意见反馈  */
//    @POST("act/mine/opinion/submit.htm")
//    abstract fun opinion(@Body sub: IdeaSub): Call<HttpResult>
//
//    /** 通话记录  */
//    @POST("act/mine/userInfo/records.htm")
//    abstract fun records(@Body sub: PhoneInfoSub): Call<HttpResult>

    /** 短信  */
    @POST("act/mine/userInfo/messages.htm")
    fun messages(@Body sub: PhoneInfoSub): Call<HttpResult<Any>>

    /** 通讯录  */
    @POST("act/mine/userInfo/contacts.htm")
    fun contacts(@Body sub: PhoneInfoSub): Call<HttpResult<Any>>

//    /** 获取认证中心图片  */
//    @GET("act/mine/userAuth/getAuthImgLogo.htm")
//    abstract fun getAuthImgLogo(): Call<HttpResult<CreditImgRec>>
//
//    /** 魔蝎调用之前  参数userId，上传通讯录到魔蝎的接口  */
//    @GET("act/mine/moxie/operator/operatorCredit.htm")
//    abstract fun moxieContacts(@Query("userId") userId: String): Call<HttpResult>
}