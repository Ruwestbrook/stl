package com.loan.stl.module.home.viewControl

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.loan.stl.BR
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.binding.BaseRecyclerViewAdapter
import com.loan.stl.common.binding.ItemClick
import com.loan.stl.databinding.ActivityLoanBinding
import com.loan.stl.module.home.viewModel.LoanTerm
import com.loan.stl.module.mine.viewModel.LoanVM
import com.loan.stl.utils.Util
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.animation.LayoutAnimationController
import android.widget.RelativeLayout
import com.loan.stl.utils.device.DeviceUtil


/**
author: russell
time: 2019-07-17:09:32
describe：借款逻辑控制
 */
class LoanControl(val activity: BaseActivity,val binding: ActivityLoanBinding) {
    /** 协议 */
    private var protocolPicker: OptionsPickerView<String?>? = null
    private  val protocolList= mutableListOf<String?>()
    /** 如何使用 */
    private var usePicker: OptionsPickerView<String?>? = null
    private val useList= mutableListOf<String?>()

    /** 收款银行卡 */
    private var cardPicker: OptionsPickerView<String?>? = null
    private  val cardList= mutableListOf<String?>()
    /** 选择还款方式 */
    private var typePicker: OptionsPickerView<String?>? = null
    private  val typeList= mutableListOf<String?>()

    private var recyclerView:RecyclerView?=null



    private  val loanVMlList= mutableListOf<LoanTerm>()

    var loanVM=LoanVM()
    init {
        testData()
        recyclerView=RecyclerView(activity)
        val padding= DeviceUtil.dp2px(activity,10)
        recyclerView?.setPadding(0,padding,0,padding)
        recyclerView?.layoutManager= GridLayoutManager(activity,4)
        val adapter=BaseRecyclerViewAdapter(loanVMlList, BR.item,R.layout.item_term)
        recyclerView?.adapter=adapter
        adapter.setItemClick(object : ItemClick{
            override fun Click(view: View, o: Any) {
                loanVMlList.forEach {
                    it.selected=false
                    if(o as LoanTerm ===it){
                        it.selected=true
                        loanVM.term=it.term
                        chooseTerm(binding.listContainer)
                    }

                }
                adapter.notifyDataSetChanged()
            }

        })

    }






    /*
    查看所有协议
     */
    fun protocol(view: View){
        Util.hideKeyBoard(view)
        protocolPicker = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, option2, options3, v ->

                /*
                打开网页
                 */
                protocolPicker?.dismiss()
            }).build<String?>()
        protocolPicker?.setTitleText("查看相关协议")
        protocolPicker?.setPicker(protocolList)
        protocolPicker?.show()

    }

    /*
    如何使用
     */

    fun use(view: View){
        Util.hideKeyBoard(view)
        usePicker = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, _, _, _ ->
                // contactVM.relation=relation[options1]
                loanVM.use=useList[options1]
            })
            .setTextColorCenter(ContextCompat.getColor(view.context,R.color.app_base_color))
            .build<String?>()
        usePicker?.setTitleText("请选择您的用途")
        usePicker?.setPicker(useList)
        usePicker?.show()
    }

    /*
     展示银行卡
     */
    fun bankCard(view: View){
        Util.hideKeyBoard(view)
        cardPicker = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, _, _, _ ->
                loanVM.card=cardList[options1]
            })
            .setTextColorCenter(ContextCompat.getColor(view.context,R.color.app_base_color))
            .build<String?>()

        cardPicker?.setTitleText("请选择您的收款银行卡")
        cardPicker?.setPicker(cardList)
        cardPicker?.show()
    }

    /*
 选择返回方式
  */
    fun returnType(view: View){
        if(!loanVM.chooseType){
            return
        }
        Util.hideKeyBoard(view)
        typePicker = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, _, _, _ ->
                // contactVM.relation=relation[options1]
                loanVM.use=useList[options1]
            })
            .setTextColorCenter(ContextCompat.getColor(view.context,R.color.app_base_color))
            .build<String?>()
        typePicker?.setTitleText("请选择还款方式")
        typePicker?.setPicker(typeList)
        typePicker?.show()
    }


    @Suppress("UNUSED_PARAMETER")
    fun chooseTerm(view: View){

        val translateAnimation: TranslateAnimation?

        if(binding.listContainer.childCount<1){

            translateAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
            )
            translateAnimation.duration = 500

            val lac = LayoutAnimationController(translateAnimation)
            binding.listContainer.layoutAnimation = lac
            val layoutParams=RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.listContainer.layoutAnimation = lac
            binding.listRotation.rotation=90F
            binding.listContainer.addView(recyclerView,layoutParams)


        }else{
            translateAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f
            )
            translateAnimation.duration = 500
            val lac = LayoutAnimationController(translateAnimation)
            binding.listContainer.layoutAnimation = lac
            binding.listRotation.rotation=0F
            binding.listContainer.removeAllViews()
        }


    }




    
    private fun testData(){


        loanVM.returnType="等额本息"
        loanVM.maxQuota=8000
        loanVM.company=activity.resources.getString(R.string.test_company_name)

        var loanTerm=LoanTerm()
        loanTerm.term="3期"
        loanTerm.selected=true
        loanVMlList.add(loanTerm)
        loanTerm=LoanTerm()
        loanTerm.term="6期"
        loanTerm.selected=false
        loanVMlList.add(loanTerm)
        loanTerm=LoanTerm()
        loanTerm.term="9期"
        loanTerm.selected=false
        loanVMlList.add(loanTerm)
        loanTerm=LoanTerm()
        loanTerm.term="12期"
        loanTerm.selected=false
        loanVMlList.add(loanTerm)

        useList.add("学习")
        useList.add("装修")
        useList.add("教育")
        useList.add("旅游")
        useList.add("医疗")
        useList.add("其他")
        useList.add("场景消费协议")


        protocolList.add("电子签章开通服务协议")
        protocolList.add("电子签章服务隐私声明")
        protocolList.add("借款协议")
        protocolList.add("场景消费协议")

        cardList.add("建设银行储蓄卡(0011)")
        cardList.add("建设银行储蓄卡(0021)")
        cardList.add("建设银行储蓄卡(0031)")
        cardList.add("建设银行储蓄卡(0041)")
        cardList.add("建设银行储蓄卡(0051)")
        cardList.add("建设银行储蓄卡(0061)")
        cardList.add("建设银行储蓄卡(0071)")

        typeList.add("等额本息")
        typeList.add("先本后息")
    }
    

}