package com.loan.stl.module.mine.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.CommonType
import com.loan.stl.common.Constant
import com.loan.stl.common.RequestResultCode
import com.loan.stl.module.mine.dataModel.CommonRec
import com.loan.stl.module.mine.dataModel.receive.CreditBankRec
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.CommonService
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.ListData
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.device.DeviceUtil
import kotlinx.android.synthetic.main.activity_add_bank_card.*
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-15:10:05
describe：查看以绑定的银行卡
 */
@Route(path = RouterUrl.BANK_CARD)
class BankCardActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bank_card)
        var barHeight = 0
        val resourceId = applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            barHeight = applicationContext.resources.getDimensionPixelSize(resourceId)
        }
        container.minHeight=DeviceUtil.deviceHeight(this)-DeviceUtil.dp2px(this,44)-barHeight
        addFooterText()
        setPageTitle(R.string.credit_bank_title_bind)
        reqData()
        setPageTitle("已绑银行卡",false)

    }

    private fun addFooterText() {
        val textView=TextView(this)
        textView.text=resources.getText(R.string.footer_text)
        textView.setTextColor(ContextCompat.getColor(this,R.color.text_color_8a))
        textView.textSize= 10F
        textView.gravity=Gravity.CENTER
        val layoutParams= ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.bottomToBottom=ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomMargin=DeviceUtil.dp2px(this,15)

         container.addView(textView,layoutParams)

    }


    /*
    添加银行卡
     */
    fun addBankCard(view: View){
        ARouter.getInstance().build(RouterUrl.BIND_CARD).navigation(this,RequestResultCode.REQ_CARD_BIND)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RequestResultCode.REQ_CARD_BIND && resultCode==Activity.RESULT_OK){
            reqData()
        }
    }

    /*
        请求数据
     */
    /** 请求银行卡信息  */
    private fun reqData() {
        val callInit = HttpClient.getService(MineService::class.java).getBankCardList()
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object : ResponseCallback<HttpResult<CreditBankRec>>() {
            override   fun onSuccess(call: Call<HttpResult<CreditBankRec>>,
                                     response: Response<HttpResult<CreditBankRec>>) {
                if (response.body() != null && response.body()!!.getData() != null) {
                    convert(response.body()!!.data)
                    if (Constant.STATUS_10 == response.body()!!.data.changeAble) {
                        addButton.visibility=View.VISIBLE
                    } else {
                        addButton.visibility=View.GONE
                    }
                }
            }
        })
        val call = HttpClient.getService(CommonService::class.java).remarkList()
        call.enqueue(object :ResponseCallback<HttpResult<ListData<CommonRec>>>(){
            override fun onSuccess(
                call: Call<HttpResult<ListData<CommonRec>>>?,
                response: Response<HttpResult<ListData<CommonRec>>>?
            ) {
                   response?.body()!!.data.list.forEach {
                   if (CommonType.BANKREMARK ==it.code) {
                       remark.text=it.value
                   }
               }

            }

        })
    }

    /** dataModel to viewModel  */
    private fun convert(rec: CreditBankRec?) {
        if(rec==null){
            card_layout.visibility=View.GONE
            no_card_layout.visibility=View.VISIBLE
            return
        }
        card_layout.visibility=View.VISIBLE
        no_card_layout.visibility=View.GONE
        card_list.layoutManager=LinearLayoutManager(this)
        card_list.adapter=object :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view=LayoutInflater.from(parent.context).inflate(R.layout.item_bank_card,parent,false)
                return object :RecyclerView.ViewHolder(view){}
            }

            override fun getItemCount(): Int {
                return 1
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val bankName=holder.itemView.findViewById<TextView>(R.id.bank_name)
                val bankID=holder.itemView.findViewById<TextView>(R.id.card)
                bankName.text=rec.bank
                bankID.text=rec.cardNo

            }

        }


    }
}