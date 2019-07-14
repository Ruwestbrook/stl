package com.loan.stl.utils;

/**
 * Created by Tracy on 2/11/16.
 */

public class Constants {
    public static final String OUTPUTTYPE = "outputtype";
    public static final String COMPLEXITY = "comlexity";
    public static final String NOTICE = "notice";
    public static final String DETECTLIST = "detectList";
    public static final String DEFAULTDETECTLIST = "BLINK MOUTH NOD YAW";


    /**
     * 魔蝎检测
     */
    public static final String ERROR_CAMERA_REFUSE = "相机权限获取失败或权限被拒绝";
    public static final String ERROR_SD_REFUSE = "SD卡权限被拒绝";
    public static final String ERROR_SD_CAMERA_PERMISSION = "相机权限被拒绝或SD卡权限被拒绝，请授权相机权限和SD卡权限";
    public static final String ERROR_PACKAGE = "未替换包名或包名错误";
    public static final String ERROR_LICENSE_OUT_OF_DATE = "授权文件过期";
    public static final String ERROR_SDK_INITIALIZE = "算法SDK初始化失败：可能是授权文件或模型路径错误，SDK权限过期，包名绑定错误";
    public static final int DAYS_BEFORE_LIC_EXPIRED = 5;
    public static final String ERROR_SCAN_CANCEL = "扫描被取消";
    public static final String ERROR_TIME_OUT = "扫描失败，扫描超时";

    /**
     * 多图
     */
    public static final String MULTIIMG = "multiImg";
    /**
     * 低质量视频
     */
    public static final String VIDEO = "video";
    /**
     * 高质量视频
     */
    public static final String FULLVIDEO = "fullVideo";

    // complexity value
    public static final String EASY = "easy";
    public static final String NORMAL = "normal";
    public static final String HARD = "hard";
    public static final String HELL = "hell";
}