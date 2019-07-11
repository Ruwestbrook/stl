package com.loan.custom.navigation;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * created by russell on 2019/5/7
 * email:igruwestbrook@gmail.com
 * Description:
 */
public class NavigationBar extends LinearLayout {
    private List<NavigationItem> items;
    private int mActiveColor=0;
    private int mInActiveColor=0;
    private int oldPosition=-1;
    private OnTabSelectedListener onTabSelectedListener;
    public NavigationBar(Context context) {
        this(context,null);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        items=new ArrayList<>();
        setBackgroundColor(Color.WHITE);
    }



    public  void addItem(NavigationItem item){
        items.add(item);
    }

    public void initialise(){
        if(items==null || items.size()<1){
            throw new RuntimeException("至少需要一个itemView.");
        }
        for (int i = 0; i < items.size(); i++) {
            NavigationItem item=items.get(i);
            if(firstSelectedPosition==i){
                item.setChoose(true);
            }else {
                item.setChoose(false);
            }
            if(mActiveColor>0){
                item.setChooseColor(mActiveColor);
            }
            if(mInActiveColor>0){
                item.setNormalColor(mInActiveColor);
            }
            NavigationItemView view=new NavigationItemView(getContext(),item);
            item.setView(view);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0
                    , ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            addView(view,layoutParams);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    choose(v);
                }
            });
        }
    }


    /**
     * @param activeColor 设置选中时文字的颜色
     * @return this, to allow builder pattern
     */
    public NavigationBar setActiveColor(int activeColor) {
        this.mActiveColor =activeColor;
        return this;
    }

    /**
     * @param inActiveColor 设置未选中时文字的颜色
     * @return this, to allow builder pattern
     */
    public NavigationBar setInActiveColor(int inActiveColor) {
        this.mInActiveColor =inActiveColor;
        return this;
    }

    private void choose(View v){
        for (int j = 0; j <items.size(); j++) {
            NavigationItem navigationItem=items.get(j);
            if(navigationItem.getView()==v){
                if(oldPosition==j){
                    return;
                }
                navigationItem.setChoose(true);
                if(onTabSelectedListener!=null){
                    onTabSelectedListener.onTabSelected(j);
                    if(oldPosition!=-1){
                        onTabSelectedListener.onTabUnselected(oldPosition);
                    }
                    oldPosition=j;
                }
            }else {
                navigationItem.setChoose(false);
            }
        }
    }
    private void choose(int position){
        for (int j = 0; j <items.size(); j++) {
            NavigationItem navigationItem=items.get(j);
            if(j==position){
                navigationItem.setChoose(true);
                if(onTabSelectedListener!=null){
                    onTabSelectedListener.onTabSelected(j);
                    if(oldPosition!=-1){
                        onTabSelectedListener.onTabUnselected(oldPosition);
                    }
                    oldPosition=j;
                }
            }else {
                navigationItem.setChoose(false);
            }
        }
    }

    public void selectTab(int position){
        choose(position);
    }
    private int firstSelectedPosition=0;
    public NavigationBar setFirstSelectedPosition(int position){
        firstSelectedPosition=position;
        oldPosition=firstSelectedPosition;
        return this;
    }

    public void setTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }
}
