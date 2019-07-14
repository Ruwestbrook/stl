package com.loan.stl.common

/**
 * Author: Hubert
 * E-mail: hbh@erongdu.com
 * Date: 2017/3/31 下午8:08
 *
 *
 * Description:
 */
class AppConfig {
     companion object{
         /** 是否是debug模式  */
         const val IS_DEBUG = true
         /** 测试服务器地址  */
         const val URI_AUTHORITY_BETA = "http://beta.interface.xjd.666news.cn/"




         //2019/07/04 russell更换
         const val SIX_APP_KEY = "75b14dd6936a4425a4cbfca08a4da5a2"
         const val SIX_APP_SECRET = "bb1da73af103e5d3f5dae00152b68761"

         /** 正式服务器地址  */
         const val URI_AUTHORITY_RELEASE = "http://jlmd.interface.666news.cn/"

         /** app_key  */
         const val APP_KEY = "oQIhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ"

         /** app_secret  */
         const val APP_SECRET = "LdB4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN"

         /** 引导页数量  */
         const val GUIDE_COUNT = 3
         /**
          * 首页下标
          * 1 展现认证数
          * 2 展现借款数
          * 3 天数按钮展示
          * 4 闪现
          * 5 速融
          * 6 基隆   2019/07/02 russell 修改页面
          */
         const val HOME_INDEX_NUM = 6
         /**
          * 认证中心下标
          * 1列表
          * 2九宫格
          * 3选题 必填区分
          */
         const val CREDIT_CENTER_INDEX_NUM = 1
         /**
          * 人像识别方案
          * 1商汤
          * 2face++
          * 3小视
          * 4智趣
          * 5友顿
          */
         const val IDENTIFICATION_TYPE = 4
         /**
          * 紧急联系人个数
          */
         const val LINKER_NUMBER = 2

         /**
          * 运营商认证方式
          * 1 web 大圣
          * 2 聚力信
          * 3 公信宝
          * 4 魔蝎   2019/07/02 russell 修改为魔蝎
          */
         const val OPERATOR_TYPE = 4
     }
    
   
}
