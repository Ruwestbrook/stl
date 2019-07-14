package com.loan.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/19 16:04
 * <p/>
 * Description: 网络请求等待动画
 */
public class CutscenesProgress extends Dialog {
    private static CutscenesProgress cutscenesProgress = null;
    private RotateAnimation animation;

    public CutscenesProgress(Context context) {
        super(context);
    }

    public CutscenesProgress(Context context, int theme) {
        super(context, theme);
    }

    public static CutscenesProgress createDialog(Context context) {
        cutscenesProgress = new CutscenesProgress(context, R.style.CustomDialog);
        cutscenesProgress.setContentView(R.layout.cutscenes_progress_layout);
        cutscenesProgress.getWindow().getAttributes().gravity = Gravity.CENTER;
        cutscenesProgress.setCanceledOnTouchOutside(false);
        return cutscenesProgress;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (cutscenesProgress == null) {
            return;
        }
        ImageView imageView = cutscenesProgress.findViewById(R.id.loadingImageView);
        animation= (RotateAnimation) AnimationUtils.loadAnimation(getContext(),R.anim.animation);
        imageView.setAnimation(animation);
        animation.start();


    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(animation!=null){
            animation.cancel();
            animation=null;
        }


    }

    /**
     * 标题
     */
    public CutscenesProgress setTitle(String strTitle) {
        super.setTitle(strTitle);
        return cutscenesProgress;
    }

    /**
     * 提示内容
     */
    public CutscenesProgress setMessage(String strMessage) {
        TextView tvMsg = (TextView) cutscenesProgress.findViewById(R.id.loadingMsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
        return cutscenesProgress;
    }
}
