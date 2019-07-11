package com.loan.custom.navigation;


import android.graphics.drawable.Drawable;


/**
 * created by russell on 2019/5/7
 * email:igruwestbrook@gmail.com
 * Description:
 */
public class NavigationItem {
    //未选中时的图标
    private Drawable normalDrawable;
    //选中时的图标
    private Drawable chooseDrawable;
    //未选中时的文字颜色
    private int normalColor;
    //选中时的文字颜色
    private int chooseColor;
    //文字
    private String title;

    private  NavigationItemView view;
    //是否时选中
    private boolean isChoose;

    public NavigationItem(Drawable chooseDrawable, String title) {
        this.chooseDrawable = chooseDrawable;
        this.title = title;
    }

    public NavigationItem(String title) {
        this.title = title;
    }

    public void setNormalDrawable(Drawable normalDrawable) {
        this.normalDrawable = normalDrawable;
    }

    public void setChooseDrawable(Drawable chooseDrawable) {
        this.chooseDrawable = chooseDrawable;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public void setChooseColor(int chooseColor) {
        this.chooseColor = chooseColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
        if(view!=null)
            view.change();
    }

    public Drawable getNormalDrawable() {
        return normalDrawable;
    }

    public Drawable getChooseDrawable() {
        return chooseDrawable;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public int getChooseColor() {
        return chooseColor;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public NavigationItemView getView() {
        return view;
    }

    public void setView(NavigationItemView view) {
        this.view = view;
    }
}
