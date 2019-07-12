package com.loan.stl.module.user.dataModel.receive

/**
 * Author: chenming
 * E-mail: cm1@erongdu.com
 * Date: 16/3/18 下午3:15
 *
 *
 * Description: 登录信息
 */
/** 刷新token值  */
/** 用户名  */
/** token  */
/** 用户ID  */
/** 头像地址  */
/** 隐藏用户名  */
data class OauthTokenMo(val refreshToken: String?,
                        var username: String?,
                        val token: String?,
                        val userId: String?,
                        val avatarPhoto: String?,
                        val hideUserName: String? )
