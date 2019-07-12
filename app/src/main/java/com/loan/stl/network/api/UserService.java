package com.loan.stl.network.api;


import com.loan.stl.module.user.dataModel.LoginSub;
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo;
import com.loan.stl.network.entity.HttpResult;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/11/17 15:44
 * <p/>
 * Description: 用户接口
 * (@Url: 不要以 / 开头)
 */
public interface UserService {
    /** 登录 */
    @POST("user/login.htm")
    Call<HttpResult<OauthTokenMo>> doLogin(@Body LoginSub sub);

//    /** 刷新令牌 */
//    @FormUrlEncoded
//    @POST("user/autoLogin.htm")
//    Call<HttpResult<OauthTokenMo>> refreshToken(@Field(RequestParams.REFRESH_TOKEN) String refreshToken);
//
//    /** 注册_验证手机号是否存在 */
//    @FormUrlEncoded
//    @POST("user/isPhoneExists.htm")
//    Call<HttpResult<IsExistsRec>> isPhoneExists(@Field(RequestParams.PHONE) String phone);
//
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
//
//    /** 666 获取验证码**/
//    @FormUrlEncoded
//    @POST("auth/apitoken")
//    Call<HttpSixResult<SMSSixToken>> getCodeSixToken(@Field(RequestParams.SIX_KEY) String key, @Field(RequestParams.SIX_SECRET) String secret);
//
//    /** 666 ya**/
//    @FormUrlEncoded
//    @POST("auth/sendsms")
//    Call<HttpSixResult<Object>> getCodeSixSend(@Field(RequestParams.SIX_MOBILE) String mobile, @Header(RequestParams.SIX_TOKEN) String token);
//
//    /** 666 ya**/
//    @FormUrlEncoded
//    @POST("auth/smsverify")
//    Call<HttpSixResult<Object>> getCodeSixVerify(@Field(RequestParams.SIX_MOBILE) String mobile,
//                                                 @Header(RequestParams.SIX_TOKEN) String token, @Field(RequestParams.SIX_CODE) String sixCode);
//
//    /** 是否能获取验证码 */
//    @FormUrlEncoded
//    @POST("user/probeSms.htm")
//    Call<HttpResult<ProbeSmsRec>> probeSms(@Field(RequestParams.PHONE) String phone, @Field(RequestParams.TYPE) String type);
//
//    /** 是否能获取验证码 */
//    @FormUrlEncoded
//    @POST("act/mine/bankCard/authSms.htm")
//    Call<HttpResult<ProbeSmsRec>> authSms(@Field(RequestParams.USER_ID) String userId, @Field(RequestParams.SMS_CODE) String smsCode);
//
//    /** 验证忘记密码验证码 万能码0000 */
//    @POST("user/verifySms.htm")
//    Call<HttpResult<ProbeSmsRec>> checkCode(@Body ValidateCodeSub sub);
//
//    /** 重置密码 */
//    @POST("user/login/forgetPwd.htm")
//    Call<HttpResult> forgetPwd(@Body ForgotSub sub);
//
//    /** 修改密码_确认提交 */
//    @POST("CustomerModifyPwdAction/modifyPassword.htm")
//    Call<HttpResult> resetPwd(@Body ResetPwdSub sub);
}
