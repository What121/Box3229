package com.bestom.boxmod.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

public class PackageInfoManager {
    private Context mContext;
    private static PackageInfoManager instance;

    public static PackageInfoManager getInstance(Context mContext) {
        if (instance == null) {
            synchronized (PackageInfoManager.class) {
                if (instance == null) {
                    instance = new PackageInfoManager(mContext);
                }
            }
        }
        return instance;
    }

    private PackageInfoManager(Context context) {
        mContext = context;
    }

    /**
     * 查询手机内非系统应用
     * @return
     */
    public List<PackageInfo> getAllApps() {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = mContext.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = paklist.get(i);
            //判断是否为非系统预装的应用程序
//            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
//                // customs applications
//                apps.add(pak);
//            }
            apps.add(pak);
        }
        return apps;
    }

    /**
     * 查询手机内所有支持分享的应用
     * @return
     */
    public List<ResolveInfo> getShareApps(){
        List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
        Intent intent=new Intent(Intent.ACTION_SEND,null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        PackageManager pManager = mContext.getPackageManager();
        mApps = pManager.queryIntentActivities(intent,PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);

        return mApps;
    }

    /**
     * 通过包名启动第三方app
     * @param packageName
     */
    public  void startThridApp(String packageName){
        try {
            Intent minIntent = mContext.getPackageManager()
                    .getLaunchIntentForPackage(packageName);
            mContext.startActivity(minIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 已知包名和类名启动应用程序
     * @param context
     * @param packageName
     * @param className
     */
    public  void startThridApp(Context context,String packageName,String className){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 已知第三方应用的包名和指定类的action启动，可以启动第三方应用的指定Activity，
     * 并且传递参数，指定Activity必须设置Action；
     * @param context
     * @param packageName
     * @param action
     * @param type
     * @param count
     */
    public  void startThridApp(Context context,String packageName,String action,String type,int count){
        try {
            Intent mIntent = new Intent();
            mIntent.setPackage(packageName);//包名
            mIntent.setAction(action);//action
            mIntent.putExtra("a", type);
            mIntent.putExtra("c", count);
            context.startActivity(mIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
