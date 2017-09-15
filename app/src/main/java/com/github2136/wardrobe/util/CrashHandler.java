package com.github2136.wardrobe.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import com.github2136.util.FileUtil;
import com.github2136.wardrobe.BuildConfig;
import com.github2136.wardrobe.base.BaseApplication;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * 日志保存
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private static CrashHandler mInstance = new CrashHandler();
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;


    private CrashHandler() { }

    /**
     * 单例模式，保证只有一个CustomCrashHandler实例存在
     */
    public static CrashHandler getInstance() { return mInstance; }

    public void setCustomCrashHanler(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 异常发生时，系统回调的函数，我们在这里处理一些操作
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            if (BuildConfig.DEBUG && mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { }
            ((BaseApplication) mContext).finallyAll();//关闭应用
            System.exit(0);
        }
    }


    /**
     * 为我们的应用程序设置自定义Crash处理
     */

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        String errorLog = saveException(mContext, ex);
        HashMap<String, String> deviceInfo = getDeviceInfo(mContext);
        submitLog(errorLog, deviceInfo);
        return true;
    }

    /**
     * 保存log如果外置存储不可用将会保存至内置存储
     */
    private String saveException(Context context, Throwable ex) {
        StringBuffer sb = getLog(ex);
        String fileName = FileUtil.createFileName("log", ".log");
        String external = FileUtil.getExternalStoragePrivateLogPath(context);
        String internal = context.getFilesDir() + File.separator + "Log";
        File file;
        if (FileUtil.isExternalStorageWritable()) {
            file = new File(external, fileName);
        } else {
            file = new File(internal, fileName);
        }
        FileUtil.saveFile(file.getAbsolutePath(), sb.toString());
        return sb.toString();
    }

    private StringBuffer getLog(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append(obtainExceptionInfo(ex));
        return sb;
    }

    /**
     * 获取一些简单的信息,软件版本，手机版本，型号等信息存放在HashMap中
     */

    private HashMap<String, String> getDeviceInfo(Context context) {
        HashMap<String, String> map = new HashMap<>();
        PackageManager mPackageManager = context.getPackageManager();
        PackageInfo mPackageInfo = null;
        try {
            mPackageInfo = mPackageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        map.put("versionName", mPackageInfo.versionName);
        map.put("versionCode", "" + mPackageInfo.versionCode);
        map.put("SDK_INT", "" + Build.VERSION.SDK_INT);
        map.put("RELEASE", "" + Build.VERSION.RELEASE);
        map.put("CODENAME", "" + Build.VERSION.CODENAME);
        map.put("MODEL", "" + Build.MODEL);
        map.put("PRODUCT", "" + Build.PRODUCT);
        map.put("MANUFACTURER", "" + Build.MANUFACTURER);
        map.put("FINGERPRINT", "" + Build.FINGERPRINT);
        return map;
    }

    /**
     * 获取系统未捕捉的错误信息
     */
    private String obtainExceptionInfo(Throwable throwable) {
        StringWriter mStringWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mStringWriter);
        throwable.printStackTrace(mPrintWriter);
        mPrintWriter.close();
        return mStringWriter.toString();
    }

    /**
     * 提交错误信息到服务器
     */
    private void submitLog(String log, HashMap<String, String> deviceInfo) {

    }
}