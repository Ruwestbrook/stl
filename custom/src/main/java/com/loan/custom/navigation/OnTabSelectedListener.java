package com.loan.custom.navigation;

/**
 * created by russell on 2019/5/7
 * email:igruwestbrook@gmail.com
 * Description:
 */
public interface OnTabSelectedListener {
    /**
     * Called when a tab enters the selected state.
     *
     * @param position The position of the tab that was selected
     */
    void onTabSelected(int position);

    /**
     * Called when a tab exits the selected state.
     *
     * @param position The position of the tab that was unselected
     */
    void onTabUnselected(int position);

}
