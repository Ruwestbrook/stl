package com.loan.stl.utils;

import android.util.Log;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by chenming
 * Created Date 17/11/8 17:39
 * mail:cm1@erongdu.com
 * Describe: 是否root
 */
public class RootUtil {
    private static String LOG_TAG = RootUtil.class.getName();

    public static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;

        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
    }

    public static boolean checkRootMethod2() {
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public static boolean checkRootMethod3() {
        if (executeCommand(SHELL_CMD.check_su_binary) != null) {
            return true;
        } else {
            return false;
        }
    }


    public static enum SHELL_CMD {
        check_su_binary(new String[]{"/system/xbin/which", "su"}),;
        String[] command;

        SHELL_CMD(String[] command) {
            this.command = command;
        }
    }

    public static ArrayList<String> executeCommand(SHELL_CMD shellCmd) {
        String line         = null;
        ArrayList<String> fullResponse = new ArrayList<String>();
        Process localProcess = null;

        try {
            localProcess = Runtime.getRuntime().exec(shellCmd.command);
        } catch (Exception e) {
            return null;
            //e.printStackTrace();
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(localProcess.getOutputStream()));
        BufferedReader in  = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));

        try {
            while ((line = in.readLine()) != null) {
                Log.d(LOG_TAG, "--> Line received: " + line);
                fullResponse.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "--> Full response was: " + fullResponse);

        return fullResponse;
    }
}
