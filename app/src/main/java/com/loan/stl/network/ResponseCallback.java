package com.loan.stl.network;

import android.view.View;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.loan.stl.network.exception.ApiException;
import com.loan.stl.utils.LogUtils;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * created by russell on 2019/3/24
 * email:igruwestbrook@gmail.com
 * Description:
 */
public abstract class ResponseCallback<T> implements Callback<T> {
    private SwipeToLoadLayout swipeLayout;
    private View view;
    private Boolean flag=false;
    public ResponseCallback() {
    }

    public ResponseCallback(SwipeToLoadLayout swipeLayout, Boolean flag) {
        this.swipeLayout = swipeLayout;
        this.flag = flag;
    }

    public ResponseCallback(View view) {
        this.view = view;
    }


    public ResponseCallback(SwipeToLoadLayout swipeLayout) {
        this.swipeLayout = swipeLayout;
    }
    public abstract void onSuccess(Call<T> call, Response<T> response);
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        hasResponse();
        if (response.isSuccessful() && response.body() != null) {
            onSuccess(call, response);
        } else {
            onFailed(call, response);
        }
    }

    public void onFailed(Call<T> call, Response<T> response) {
        if(response == null || response.message().equals("")){
            return;
        }
//        if (response.code() > 400) {
//            Logger.toast(MarketApplication.getMarketContext().getResources().getString(R.string.app_network_error));
//        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
        LogUtils.Companion.d(t.toString());
        hasResponse();
        if (t instanceof ApiException && (!flag)) {

            ExceptionHandling.operate(((ApiException) t).getResult());
            return;
        }
        if(t instanceof IOException){
            return;
        }

        onFailed(call, null);
    }

    private void hasResponse(){
        NetworkUtil.dismissCutscenes();
        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        if (swipeLayout != null && swipeLayout.isLoadingMore()) {
            swipeLayout.setLoadingMore(false);
        }
        if(view!=null && !view.isEnabled()){
            view.setEnabled(true);
        }
    }

}
