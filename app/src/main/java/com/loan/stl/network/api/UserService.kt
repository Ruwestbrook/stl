package com.loan.stl.network.api


import com.loan.stl.module.user.dataModel.receive.IsExistsRec
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.module.user.dataModel.receive.ProbeSmsRec
import com.loan.stl.module.user.dataModel.sixSMS.SMSSixToken
import com.loan.stl.module.user.dataModel.submit.ForgotSub
import com.loan.stl.module.user.dataModel.submit.LoginSub
import com.loan.stl.module.user.dataModel.submit.ValidateCodeSub
import com.loan.stl.network.RequestParams
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.HttpSixResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/11/17 15:44
 *
 *
 * Description: 用户接口
 * (@Url: 不要以 / 开头)
 */
interface UserService {
    /** 登录  */
    @POST("user/login.htm")
    fun doLogin(@Body sub: LoginSub): Call<HttpResult<OauthTokenMo>>

        /** 刷新令牌 */
        @FormUrlEncoded
        @POST("user/autoLogin.htm")
        fun refreshToken(@Field(RequestParams.REFRESH_TOKEN)  refreshToken:String):Call<HttpResult<OauthTokenMo>>

    //    /** 注册_验证手机号是否存在 */
    @FormUrlEncoded
    @POST("user/isPhoneExists.htm")
    fun isPhoneExists(@Field(RequestParams.PHONE) phone: String): Call<HttpResult<IsExistsRec>>

    //    /** 注册_验证手机号 */
    //    @FormUrlEncoded
    //    @POST("CustomerRegisteredAction/validMobile.htm")
    //    Call<HttpResult> validMobile(@Field(RequestParams.PHONE) String phone);
    //
    //    /** 注册_获取验证码 */
    //    @FormUrlEncoded
    //    @POST("user/fetchSmsVCode.htm")
    //    Call<HttpResult> getRegisterCode(@Field(RequestParams.PHONE) String phone);
    //
    //    /** 注册_确认注册 */
    //    @POST("user/register.htm")
    //    Call<HttpResult<OauthTokenMo>> doRegister(@Body RegisterSub sub);
    //
    //    /** 忘记密码_获取验证码 */
    //    @FormUrlEncoded
    //    @POST("CustomerLoginAction/sendVerificationCode.htm")
    //    Call<HttpResult> getForgotCode(@Field(RequestParams.MOBILE) String phone);
    //
    //    /** 注册_获取验证码 */
    //    @FormUrlEncoded
    //    @POST("user/sendSms.htm")
    //    Call<HttpResult<ProbeSmsRec>> getCode(@Field(RequestParams.PHONE) String phone, @Field(RequestParams.TYPE) String type, @Field(RequestParams.SIGN) String sign);

        /** 666 获取验证码**/
        @FormUrlEncoded
        @POST("auth/apitoken")
        fun getCodeSixToken(@Field(RequestParams.SIX_KEY)key:String, @Field(RequestParams.SIX_SECRET)  secret:String):Call<HttpSixResult<SMSSixToken>>

        /** 666 ya**/
        @FormUrlEncoded
        @POST("auth/sendsms")
        fun getCodeSixSend(@Field(RequestParams.SIX_MOBILE)  mobile:String, @Header(RequestParams.SIX_TOKEN)  token:String):Call<HttpSixResult<Any>>

        /** 666 ya**/
        @FormUrlEncoded
        @POST("auth/smsverify")
        fun getCodeSixVerify(@Field(RequestParams.SIX_MOBILE)  mobile:String,
                                                     @Header(RequestParams.SIX_TOKEN) token:String, @Field(RequestParams.SIX_CODE)  sixCode:String):Call<HttpSixResult<Any>>

    //    /** 是否能获取验证码 */
    //    @FormUrlEncoded
    //    @POST("user/probeSms.htm")
    //    Call<HttpResult<ProbeSmsRec>> probeSms(@Field(RequestParams.PHONE) String phone, @Field(RequestParams.TYPE) String type);
    //
    //    /** 是否能获取验证码 */
    //    @FormUrlEncoded
    //    @POST("act/mine/bankCard/authSms.htm")
    //    Call<HttpResult<ProbeSmsRec>> authSms(@Field(RequestParams.USER_ID) String userId, @Field(RequestParams.SMS_CODE) String smsCode);

        /** 验证忘记密码验证码 万能码0000 */
        @POST("user/verifySms.htm")
        fun checkCode(@Body  sub: ValidateCodeSub):Call<HttpResult<ProbeSmsRec>>

        /** 重置密码 */
        @POST("user/login/forgetPwd.htm")
        fun forgetPwd(@Body sub: ForgotSub):Call<HttpResult<Any>>

    //    /** 修改密码_确认提交 */
    //    @POST("CustomerModifyPwdAction/modifyPassword.htm")
    //    Call<HttpResult> resetPwd(@Body ResetPwdSub sub);
}
