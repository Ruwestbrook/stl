package com.loan.stl.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

/**
 * created by russell on 2019/3/12
 * email:igruwestbrook@gmail.com
 * Description:dataBinding的图片加载类
 */
public class BindingImage {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url){
        /*
        applyDefaultRequestOptions(new RequestOptions().placeholder(
                        LoanApplication.getContext().getResources().
                                getDrawable(R.drawable.icon_no_icon)
                )).
         */
        Glide.with(imageView.getContext()).

                load(url).into(imageView);
    }

}
