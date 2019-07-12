package com.loan.stl.network;

import android.content.Intent;
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo;
import com.loan.stl.network.entity.HttpResult;
import com.loan.stl.utils.SPreferences.SharedInfo;
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
  static void operate(final HttpResult result) {
    /*    switch (result.getCode()) {
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
                goIndex();
            }
            break;

        case AppResultCode.TOKEN_REFRESH_TIMEOUT:
            UserLogic.signOut();
            goIndex();
            break;
        case AppResultCode.TOKEN_NOT_UNIQUE:
        case AppResultCode.TOKEN_NOT_EXIT:
            TipDialog.createDialogNormal( R.string.user_login_two,R.string.user_login_reset,
                    new ViewClick() {
                @Override
                public void click(int i) {
                    UserLogic.signOut();
                    if(i==-1){
                        goIndex();
                    }else {
                        Routers.openForResult(ActivityManage.peek(),
                                RouterUrl.getRouterUrl(RouterUrl.USER_LOGIN+"0"), 0x11);
                    }
                    if(TipDialog.tipDialogs!=null){
                        TipDialog.tipDialogs.dismiss();
                    }
                }
            });
//            DialogUtils.showDialog(ActivityManage.peek(), R.string.user_login_reset, R.string.user_login_two, new OnSweetClickListener() {
//                @Override
//                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    UserLogic.signOut();
//                    Routers.openForResult(ActivityManage.peek(), RouterUrl.getRouterUrl(String.format(RouterUrl.UserInfoManage_Login, Constant.STATUS_3))
//                            , 0);
//                    sweetAlertDialog.dismissWithAnimation();
//                }
//            }, new OnSweetClickListener() {
//                @Override
//                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    UserLogic.signOut();
//                    Routers.openForResult(ActivityManage.peek(), RouterUrl.getRouterUrl(String.format(RouterUrl.UserInfoManage_Login, Constant.STATUS_3))
//                            , 0);
//                    sweetAlertDialog.dismissWithAnimation();
//                }
//            });
            break;
          case 400:
              Log.d("result",result.getMsg());
              if(!check(result.getMsg()))
                Logger.toast(result.getMsg());
              break;
        default:
            break;
    }*/
}

    private static boolean check(String msg) {
        return msg.equals("没有token或signMsg") ||
//                msg.contains("服务器") ||
                msg.contains("验签不通过") ||
                msg.contains("系统出错了") ||
                msg.equals("版本过低，请卸载旧版本并更新至最新版本！");
    }

    private static void goIndex(){

    }
}
