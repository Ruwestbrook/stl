package com.loan.custom.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

/**
 * created by russell on 2019/5/7
 * email:igruwestbrook@gmail.com
 * Description:
 */
public class NavigationItemView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private NavigationItem item;
    private Context mContext;
    public NavigationItemView(Context context) {
        this(context,null,0);
    }
    public NavigationItemView(Context context, NavigationItem item) {
        this(context);
        this.item=item;
        textView.setText(item.getTitle());
        setOrientation(LinearLayout.VERTICAL);
        change();
    }
    public NavigationItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavigationItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    public void change() {
        if(item.isChoose()){
            imageView.setImageDrawable(item.getChooseDrawable());
            textView.setTextColor(mContext.getResources().getColor(item.getChooseColor()));
        }else {
            imageView.setImageDrawable(item.getNormalDrawable());
            textView.setTextColor(mContext.getResources().getColor(item.getNormalColor()));
        }
    }
    private void init(){

        imageView=new ImageView(getContext());
        LinearLayout.LayoutParams imageParams=new LinearLayout.LayoutParams(
                dp2px(25),
                dp2px(25));
        imageParams.gravity=Gravity.CENTER;
        imageParams.topMargin=imageParams.bottomMargin=dp2px(4);

        addView(imageView,imageParams);


        textView=new TextView(getContext());
        LinearLayout.LayoutParams textParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.bottomMargin=dp2px(4);
        textParams.gravity= Gravity.CENTER;
        textView.setIncludeFontPadding(false);
        textView.setTextSize(12);
        addView(textView,textParams);
    }
    public  int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, mContext.getResources().getDisplayMetrics());
    }
}
