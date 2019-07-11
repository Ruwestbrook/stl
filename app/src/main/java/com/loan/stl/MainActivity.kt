package com.loan.stl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.loan.custom.navigation.NavigationBar
import com.loan.custom.navigation.NavigationItem
import com.loan.custom.navigation.OnTabSelectedListener
import com.loan.stl.module.home.ui.fragment.HomeFragment
import com.loan.stl.module.home.ui.fragment.MineFragment
import com.loan.stl.module.home.ui.fragment.RepayFragment
import com.loan.stl.module.home.ui.fragment.StrategyFragment
import com.loan.stl.utils.Logger

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var repayFragment: RepayFragment
    private lateinit var strategyFragment: StrategyFragment
    private lateinit var mineFragment: MineFragment
    private lateinit var mNavigationBar:NavigationBar
    private val mListener: OnTabSelectedListener=object :OnTabSelectedListener{
        override fun onTabSelected(position: Int) {
            val transaction=supportFragmentManager.beginTransaction()
            when(position){
                0->{
                    if(supportFragmentManager.findFragmentByTag("homeFragment")==null)
                        transaction.add(R.id.container,homeFragment,"homeFragment")
                    else
                        transaction.show(homeFragment)
                }
                1->{
                    if(supportFragmentManager.findFragmentByTag("repayFragment")==null)
                        transaction.add(R.id.container,repayFragment,"repayFragment")
                    else
                        transaction.show(repayFragment)
                }
                2->{
                    if(supportFragmentManager.findFragmentByTag("strategyFragment")==null)
                        transaction.add(R.id.container,strategyFragment,"strategyFragment")
                    else
                        transaction.show(strategyFragment)
                }
                3->{
                    if(supportFragmentManager.findFragmentByTag("mineFragment")==null)
                        transaction.add(R.id.container,mineFragment,"mineFragment")
                    else
                        transaction.show(mineFragment)
                }
            }
            transaction.commit()
        }

        override fun onTabUnselected(position: Int) {
            val transaction=supportFragmentManager.beginTransaction()
            when(position){
                0->{
                    transaction.hide(homeFragment)
                }
                1->{
                    transaction.hide(repayFragment)
                }
                2->{
                    transaction.hide(strategyFragment)
                }
                3->{
                    transaction.hide(mineFragment)
                }
            }
            transaction.commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavigationBar=findViewById(R.id.tabs)
        Logger.d("onCreate")
        initBar()
    }


    private fun initBar() {

        homeFragment= HomeFragment.getInstance()
        repayFragment= RepayFragment.getInstance()
        strategyFragment= StrategyFragment.getInstance()
        mineFragment= MineFragment.getInstance()



        mNavigationBar.setActiveColor(R.color.app_base_color)
            .setInActiveColor(R.color.text_color_bd)

        var chooseDrawable = ContextCompat.getDrawable(this,R.drawable.icon_home_select)
        var normalDrawable = ContextCompat.getDrawable(this,R.drawable.icon_home)

        var item = NavigationItem(chooseDrawable, "借钱")
        item.normalDrawable = normalDrawable
        mNavigationBar.addItem(item)


        chooseDrawable = ContextCompat.getDrawable(this,R.drawable.icon_repay_select)
        normalDrawable = ContextCompat.getDrawable(this,R.drawable.icon_repay)
        item = NavigationItem(chooseDrawable, "还款")
        item.normalDrawable = normalDrawable
        mNavigationBar.addItem(item)


        chooseDrawable = ContextCompat.getDrawable(this,R.drawable.icon_find_select)
        normalDrawable = ContextCompat.getDrawable(this,R.drawable.icon_find)
        item = NavigationItem(chooseDrawable, "发现")
        item.normalDrawable = normalDrawable
        mNavigationBar.addItem(item)


        chooseDrawable = ContextCompat.getDrawable(this,R.drawable.icon_mine_select)
        normalDrawable = ContextCompat.getDrawable(this,R.drawable.icon_mine)
        item = NavigationItem(chooseDrawable, "我的")
        item.normalDrawable = normalDrawable
        mNavigationBar.addItem(item)

        mNavigationBar.setFirstSelectedPosition(0) //设置默认选中位置
            .initialise()
        mNavigationBar.setTabSelectedListener(mListener)
        mListener.onTabSelected(0)
    }
}
