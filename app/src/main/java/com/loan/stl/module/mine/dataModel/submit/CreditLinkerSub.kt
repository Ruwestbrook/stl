package com.loan.stl.module.mine.dataModel.submit

/**
 * Author: Chenming
 * E-mail: cm1@erongdu.com
 * Date: 2017/2/21 上午10:47
 *
 *
 * Description: 联系人提交
 */
data class CreditLinkerSub (
    /** 新增就不传，更新传下  */
    var id: String? = null,
    /** 姓名  */
    var name: String? = null,
    /** 电话号码  */
    var phone: String? = null,
    /** 关系(中文)  */
    var relation: String? = null,
    /** 是否直系,10直系，20其他	  */
    var type: String? = null,
    /** mac地址  */
    var mac: String? = null,
    /** 操作系统  */
    var operatingSystem: String? = null,
    /** 手机品牌  */
    var phoneBrand: String? = null,
    /** 手机设备标识  */
    var phoneMark: String? = null,
    /** 手机型号  */
    var phoneType: String? = null,
    /** 系统版本  */
    var systemVersions: String? = null,
    /** 应用build号  */
    var versionCode: String? = null,
    /** 应用版本号  */
    var versionName: String? = null,
    /** APP安装时间  */
    var appInstallTime: String? = null,
    /** APP应用市场  */
    var appMarket: String? = null
)
