package com.loan.stl.network;

import android.content.Intent;

import com.authreal.util.ToastUtil;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.loan.stl.R;
import com.loan.stl.common.ActivityManage;
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo;
import com.loan.stl.module.user.ui.activity.UserLogic;
import com.loan.stl.network.api.UserService;
import com.loan.stl.network.entity.HttpResult;
import com.loan.stl.utils.DialogUtils;
import com.loan.stl.utils.SPreferences.SharedInfo;
import com.loan.stl.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/5/30 11:53
 * <p/>
 * Description: 异常处理
 */
@SuppressWarnings("unchecked")
final class ExceptionHandling {
    public static boolean isToast = false;
    static void operate(final HttpResult result) {
        switch (result.getCode()) {
            case AppResultCode.TOKEN_TIMEOUT:
                OauthTokenMo tokenMo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
                if (null != tokenMo) {
                    Call<HttpResult<OauthTokenMo>> call = HttpClient.getService(UserService.class).refreshToken(tokenMo.getRefreshToken());
                    call.enqueue(new ResponseCallback<HttpResult<OauthTokenMo>>() {
                        @Override
                        public void onSuccess(Call<HttpResult<OauthTokenMo>> call, Response<HttpResult<OauthTokenMo>> response) {
                            SharedInfo.getInstance().saveEntity(response.body().getData());
                        }
                    });
                } else {
                    UserLogic.signOut();
                    goHome();
                }
                break;

            case AppResultCode.TOKEN_REFRESH_TIMEOUT:
                UserLogic.signOut();
                goHome();
                break;
            case AppResultCode.TOKEN_NOT_UNIQUE:
            case AppResultCode.TOKEN_NOT_EXIT:
                if(!isToast) {
                    isToast = true;
                    DialogUtils.showDialog(ActivityManage.peek(), R.string.user_login_reset, R.string.user_login_two, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            isToast = false;
                            UserLogic.signOut();
                            goHome();
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    }, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            isToast = false;
                            UserLogic.signOut();
                            goLogin();
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
                break;

            default:
                break;
        }
        if (result.getCode() != 410 && result.getCode() != 413 && result.getCode() != 412 && result.getCode() != 411) {
            ToastUtils.toast(result.getMsg());
        }
    }

    private static void goLogin() {
    }

    private static void goHome() {

//        Routers.openForResult(ActivityManage.peek(), RouterUrl.getRouterUrl(String.format(RouterUrl.UserInfoManage_Login, Constant
//                        .STATUS_3))
//                , 0);
    }
}
