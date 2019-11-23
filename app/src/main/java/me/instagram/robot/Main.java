package me.instagram.robot;

import android.content.Context;
import android.content.Intent;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class Main implements IXposedHookLoadPackage {

    public void handleCoreModule(final LoadPackageParam loadPackageParam) throws Throwable {

        // com.instagram.android.activity.BROADCAST_REFRESH_MAIN_FEED 过滤掉这个通知，就不会从前台切换到后台时自动滚到最顶部了
        final Class cls_common_o_i = findClass("com.instagram.common.o.i"
                , loadPackageParam.classLoader);
        findAndHookMethod(cls_common_o_i
                , "onReceive"
                , Context.class
                , Intent.class
                , new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Intent intent = (Intent) param.args[1];
                        String action = intent.getAction();
                        if (action.equals("com.instagram.android.activity.BROADCAST_REFRESH_MAIN_FEED")) {
                            intent.setAction("com.instagram.android.activity.vanish.BROADCAST_REFRESH_MAIN_FEED");
                        }
                    }
                });

    }

    @Override
    public void handleLoadPackage(final LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(VersionMapping._TPN))
            return;

        if (!loadPackageParam.processName.equals(VersionMapping._TPN))
            return;

        // 初始化核心部分
        handleCoreModule(loadPackageParam);
    }
}
