package namret.bancontactsecuritydisabler;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * This module bypasses the security in the Bancontact app
 * Created by namret on 21/10/2015 (Great Scott!).
 * Last updated: 22/10/2015
 */
public class SecurityBypass implements IXposedHookLoadPackage {

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable
    {
        if (
                lpparam.packageName.equals("mobi.inthepocket.bcmc.bancontact")
                )
        {
            XposedBridge.log("Loaded " + lpparam.packageName + ", hooking root methods");

            try
            {
                findAndHookMethod("o.v", lpparam.classLoader, "ʼ", "o.ﾒ", XC_MethodReplacement.returnConstant(0));
                findAndHookMethod("o.v", lpparam.classLoader, "ʻ", "o.ﾒ", XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("o.v", lpparam.classLoader, "ˊ", String.class, "o.ﾒ", XC_MethodReplacement.returnConstant(null));

                findAndHookMethod("ᴵ", lpparam.classLoader, "ˊ", XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("ᴵ", lpparam.classLoader, "ˋ", Context.class,  XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("ᴵ", lpparam.classLoader, "ˋ", XC_MethodReplacement.returnConstant(false));
                aUX aux = new aUX();
                aux.ˊ = Boolean.toString(false);
                aux.ˋ = "detectFlag:" + true + " | detectUsbDebug:" + false;

                //Method not working (bypassing USB Debugging detection) -> immediate fail
                //findAndHookMethod("ˑ", lpparam.classLoader, "ˊ", Context.class, XC_MethodReplacement.returnConstant(aux));
            }
            catch (NoSuchMethodError e)
            {
                XposedBridge.log("Method not found " + e.getMessage());
            }
        }
    }
}


