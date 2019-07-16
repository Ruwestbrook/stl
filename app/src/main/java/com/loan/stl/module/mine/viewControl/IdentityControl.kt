package com.loan.stl.module.mine.viewControl

import android.view.View
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.loan.stl.common.DicKey
import com.loan.stl.module.mine.dataModel.receive.DicRec
import com.loan.stl.module.mine.dataModel.receive.KeyValueRec
import com.loan.stl.module.mine.viewModel.IdentityVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

/**
author: russell
time: 2019-07-15:23:37
describe：
 */
class IdentityControl {
    var identityVm=IdentityVM()
    private val wokeType = ArrayList<String?>()
    private val salary = ArrayList<String?>()
    init {
        reqDic()
        wokeType.add("党政机关/事业单位")
        wokeType.add("公司职员")
        wokeType.add("工人/服务员")
        wokeType.add("生意人/个体户")
        wokeType.add("学生")
        wokeType.add("学生")
        wokeType.add("自由职业")
        wokeType.add("其他")
    }

    /**
     * 数据字典请求
     *
     * @param view
     */
    private fun reqDic() {
        val callInit = HttpClient.getService(MineService::class.java).getDicts(DicKey.SALARYRANGE)
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object : ResponseCallback<HttpResult<DicRec>>() {
            override fun onSuccess(call: Call<HttpResult<DicRec>>,
                                   response: Response<HttpResult<DicRec>>
            ) {
                val dic = response.body()!!.data
                if (dic != null) {//数据字典获取内容
                    val temp: List<KeyValueRec>
                    if (dic.salaryRangeList != null) {
                        temp = dic.salaryRangeList
                        for (i in temp.indices) {
                            salary.add(temp[i].value)
                        }
                    }

                }

            }
        })
    }

    /*
        请选择工资类型
     */
    fun salary(view: View){
        val pvOptions = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, option2, options3, v ->
                identityVm.salary=salary[options1]
            })
            .setTitleText("请选择您月收入(税后)")
            .build<String?>()
        pvOptions.setPicker(salary as List<String?>?)
        pvOptions.show()
    }
    /*
    请选择工资类型
     */
    fun workType(view: View){
        val pvOptions = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, option2, options3, v ->
                identityVm.workType=wokeType[options1]
            })
            .setTitleText("请选择您的真实职业身份")
            .build<String?>()
        pvOptions.setPicker(wokeType as List<String?>?)
        pvOptions.show()
    }

    /*
        提交
     */
    fun submit(view: View){

    }
}