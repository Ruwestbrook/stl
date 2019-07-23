package com.loan.stl.module.home.viewControl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loan.stl.R
import com.loan.stl.common.CommonControl
import com.loan.stl.databinding.FragmentStrategyBinding

/**
author: russell
time: 2019-07-19:16:36
describe：
 */
class StrategyControl(val binding: FragmentStrategyBinding):CommonControl() {

    init {
        val manger=LinearLayoutManager(binding.root.context)
        manger.orientation=LinearLayoutManager.HORIZONTAL
        binding.horizontalList.layoutManager=manger
        binding.horizontalList.adapter=object :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view=LayoutInflater.from(parent.context).inflate(R.layout.item_strategy_hot,parent,false)
                return object :RecyclerView.ViewHolder(view){}
            }

            override fun getItemCount(): Int {
                return 10
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }



        binding.verticalList.layoutManager=LinearLayoutManager(binding.root.context)
        binding.verticalList.adapter=object :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view=LayoutInflater.from(parent.context).inflate(R.layout.item_strategy_info_one,parent,false)
                return object :RecyclerView.ViewHolder(view){}
            }

            override fun getItemCount(): Int {
                return 20
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

    }



}