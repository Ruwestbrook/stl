/*  Permission Group	                        Permissions
 *  android.permission-group.CALENDAR           android.permission.READ_CALENDAR
 *                                              android.permission.WRITE_CALENDAR
 *
 *  android.permission-group.CAMERA             android.permission.CAMERA
 *
 *  android.permission-group.CONTACTS           android.permission.READ_CONTACTS
 *                                              android.permission.WRITE_CONTACTS
 *                                              android.permission.GET_ACCOUNTS
 *
 *  android.permission-group.LOCATION           android.permission.ACCESS_FINE_LOCATION
 *                                              android.permission.ACCESS_COARSE_LOCATION
 *
 *  android.permission-group.MICROPHONE         android.permission.RECORD_AUDIO
 *
 *  android.permission-group.PHONE              android.permission.READ_PHONE_STATE
 *                                              android.permission.CALL_PHONE
 *                                              android.permission.READ_CALL_LOG
 *                                              android.permission.WRITE_CALL_LOG
 *                                              com.android.voicemail.permission.ADD_VOICEMAIL
 *                                              android.permission.USE_SIP
 *                                              android.permission.PROCESS_OUTGOING_CALLS
 *
 *  android.permission-group.SENSORS            android.permission.BODY_SENSORS
 *  android.permission-group.SMS                android.permission.SEND_SMS
 *                                              android.permission.RECEIVE_SMS
 *                                              android.permission.READ_SMS
 *                                              android.permission.RECEIVE_WAP_PUSH
 *                                              android.permission.RECEIVE_MMS
 *                                              android.permission.READ_CELL_BROADCASTS
 *
 *  android.permission-group.STORAGE            android.permission.READ_EXTERNAL_STORAGE
 *                                              android.permission.WRITE_EXTERNAL_STORAGE
 */
package com.loan.stl.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import com.loan.stl.R;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/22 19:33
 * <p>
 * Description: 运行时权限校验工具类
 * 在Application中调用 PermissionCheck.init(...)方法
 */
@SuppressWarnings("unused")
public class PermissionCheck {
    /** 存储相关权限 */
    public static final int REQUEST_CODE_STORAGE = 0xAAA1;
    /** 电话相关权限 */
    public static final int REQUEST_CODE_PHONE   = 0xAAA2;
    /** 短信相关权限 */
    public static final int REQUEST_CODE_SMS     = 0xAAA3;
    /** 申请所有授权 */
    public static final int REQUEST_CODE_ALL     = 0xAAAA;
    /** 根目录 */
    private static String ROOT_PATH;
    /** 提示文字 */
    private static String TOAST;

    private PermissionCheck() {
    }

    public static PermissionCheck getInstance() {
        return PermissionCheckInstance.instance;
    }

    private static class PermissionCheckInstance {
        static PermissionCheck instance = new PermissionCheck();
    }

    /**
     * 初始化
     *
     * @param root_path
     *         根目录
     * @param toast
     *         提示文字
     *         E.G.  为了能更好的使用本应用，请授予如下权限。
     */
    public static void init(String root_path, String toast) {
        ROOT_PATH = root_path;
        TOAST = toast;
    }

    /**
     * 申请权限
     *
     * @param activity
     *         activity
     * @param permissions
     *         权限列表
     *         <p>
     *         Manifest.permission.WRITE_EXTERNAL_STORAGE
     *         Manifest.permission.CALL_PHONE
     *         Manifest.permission.READ_SMS
     *         Manifest.permission.RECEIVE_SMS
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void askForPermissions(Activity activity, List<String> permissions, int requestCode) {
        // 如果不是android6.0以上的系统，则不需要检查是否已经获取授权
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        List<String> permissionTemp = new ArrayList<>();
        permissionTemp.addAll(permissions);
        for (String permission : permissions) {
            // PackageManager.PERMISSION_GRANTED    授予权限
            // PackageManager.PERMISSION_DENIED     没有权限
            // 如果已经授予该权限，则从请求授予列表中去除
            System.out.println("permission" + permission);
            if (selfPermissionGranted(activity, permission)) {
                permissionTemp.remove(permission);
            }
        }
        if (hasAlwaysDeniedPermission(activity, permissionTemp)) {
            showAskDialog(activity);
        } else {
            System.out.println("permissionTemp:" + permissionTemp.size());
            if (permissionTemp.size() > 0) {
                activity.requestPermissions(permissionTemp.toArray(new String[permissionTemp.size()]), requestCode);
            }
        }
    }

    public void askForPermissions(Activity activity, String[] permissions, int requestCode) {
        if (null == permissions || permissions.length <= 0) {
            return;
        }
        System.out.println("askForPermissions");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, permissions);
        askForPermissions(activity, list, requestCode);
    }

    /**
     * 校验权限
     *
     * @param context
     *         context
     * @param permission
     *         需要校验的权限
     *
     * @return 是否授予该权限
     * true - 授予
     * false - 还未授予
     */
    public boolean checkPermission(Context context, String permission) {
        // 如果不是android6.0以上的系统，则不需要检查是否已经获取授权
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        int verify = ContextCompat.checkSelfPermission(context, permission);
        // PackageManager.PERMISSION_GRANTED    授予权限
        // PackageManager.PERMISSION_DENIED     没有权限
        return verify == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求授权后的回调类
     *
     * @param activity
     *         activity
     * @param requestCode
     *         requestCode
     * @param permissions
     *         权限列表
     * @param grantResults
     *         授权结果
     */
    public void onRequestPermissionsResult(final Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_PHONE || requestCode == REQUEST_CODE_SMS || requestCode == REQUEST_CODE_ALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获取权限后，做初始化操作，例如：创建ROOT目录，读取联系人到DATA中等。
                initApp(requestCode);
            }
        }
        Log.e("permission", "onRequestPermissionsResult");
        Log.e("requestCode", requestCode + "");
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_ALL) {
            needAskAgainForPermissions(activity, permissions);
        }
    }

    /**
     * 有权限后，初始化操作
     */
    private void initApp(int requestCode) {
        if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_ALL) {
            File filePath = new File(ROOT_PATH);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
        }
    }

    /**
     * 是否需要对没授权，且被拒绝过一次AND不再提醒的重要权限进行二次申请
     *
     * @param activity
     *         activity
     * @param permissions
     *         权限列表
     *
     * @return 是否需要
     * true - 需要再次申请
     * false - 不需要再次申请
     */
    private boolean needAskAgainForPermissions(Activity activity, String[] permissions) {
        for (String permission : permissions) {
            switch (permission) {
                // 与APP稳定性、体验等相关的重要的运行时权限，进行提示
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    // 是否可以弹出一个解释申请该权限的提示给用户，如果为true，则可以弹
                    if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
                            && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                        // false，则自己弹出提示，也可以Intent到系统的APP setting界面
                        showAskDialog(activity);
                        return true;
                    }

                default:
                    break;
            }
        }
        return false;
    }

    /**
     * 显示提示dialog
     */
    private void showAskDialog(final Activity activity) {
        new AlertDialog.Builder(activity).setMessage(R.string.permission_message_permission_failed)
                .setNegativeButton("取消", null)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 去设置中设置权限
                        try {
                            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);

                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + info.packageName));
                            activity.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).create().show();
    }

    public static boolean selfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result           = true;
        int     targetSdkVersion = -1;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
            Log.d("permission", permission + result);
        }
        return result;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param activity
     *         {@link Activity}.
     * @param deniedPermissions
     *         one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Activity activity, @NonNull List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(activity, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint("LongLogTag")
    public static boolean hasAlwaysDeniedPermission(@NonNull Activity activity, @NonNull String[] deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            Log.d("hasAlwaysDeniedPermission", deniedPermission);
            Log.d("shouldShowRationalePermissions(activity, deniedPermission)", ""+shouldShowRationalePermissions(activity, deniedPermission));
            if (!shouldShowRationalePermissions(activity, deniedPermission)) {
                Log.d("hasAlwaysDeniedPermission", deniedPermission);
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment
     *         {@link Fragment}.
     * @param deniedPermissions
     *         one or more permissions.
     *
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Fragment fragment, @NonNull
            List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    static boolean shouldShowRationalePermissions(Object o, String... permissions) {
        boolean rationale = false;
        boolean isOk = false;
        for (String permission : permissions) {
            if (o instanceof Activity) {
                Log.d("rationale","Activity" + ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission));
                rationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission);
                isOk = selfPermissionGranted((Activity) o, permission);
            } else if (o instanceof Fragment) {
                Log.d("rationale","Fragment");
                rationale = ((Fragment) o).shouldShowRequestPermissionRationale(permission);
                isOk = selfPermissionGranted(((Fragment) o).getActivity(), permission);
            }
            Log.d("rationale",rationale + "");
            //判断是否已经获取授权
            if(isOk)
                return true;
            //判断是否拒绝弹出系统提示框
            if (rationale)
                return true;
        }
        return false;
    }
}
