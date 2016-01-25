package org.kei.android.atk.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.kei.android.atk.utils.fx.Fx;
import org.kei.android.atk.view.IThemeActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 *******************************************************************************
 * @file Tools.java
 * @author Keidan
 * @date 01/09/2015
 * @par Project
 * ATK
 *
 * @par 
 * Copyright 2015 Keidan, all right reserved
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY.
 *
 * License summary : 
 *    You can modify and redistribute the sources code and binaries.
 *    You can send me the bug-fix
 *
 * Term of the license in in the file license.txt.
 *
 *******************************************************************************
 */
public class Tools {
  public static final String TAG              = Tools.class.getSimpleName();
  public static final File   DEFAULT_ROOT     = Environment
                                                  .getExternalStorageDirectory();
  public static final File   DEFAULT_DOWNLOAD = Environment
                                                  .getExternalStoragePublicDirectory(
                                                      Environment.DIRECTORY_DOWNLOADS);
  public static final int TOAST_LENGTH_SHORT  = Toast.LENGTH_SHORT;
  public static final int TOAST_LENGTH_LONG   = Toast.LENGTH_LONG;
  
  public static int getPrefInt(final Context c, final String key, final int defValue) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
    return sp.getInt(key, defValue);
  }
  public static boolean getPrefBoolean(final Context c, final String key, final boolean defValue) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
    return sp.getBoolean(key, defValue);
  }
  public static String getPrefString(final Context c, final String key, final String defValue) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
    return sp.getString(key, defValue);
  }
  
  public static void restartApplication(final Activity a) {
    Intent i = a.getBaseContext().getPackageManager().getLaunchIntentForPackage(a.getBaseContext().getPackageName() );
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    a.startActivity(i);
  }
  
  public static void toast(final Context context, int icon, CharSequence message, int delay) {
    Toast toast = Toast.makeText(context, message, delay);
    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
    if (null!=tv) {
      Drawable drawable = Fx.getDrawable(context, icon);
      tv.setCompoundDrawablesWithIntrinsicBounds(Fx.resize(context, drawable, 32, 32), null, null, null);
      tv.setCompoundDrawablePadding(5);
    }
    toast.show();
  }
  
  public static void toast(final Context context, int icon, CharSequence message) {
    toast(context, icon, message, TOAST_LENGTH_SHORT);
  }

  public static void showAlertDialog(final Context c, final String title,
      final String message) {
    AlertDialog alertDialog = new AlertDialog.Builder(c).create();
    alertDialog.setTitle(title);
    alertDialog.setMessage(message);
    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    alertDialog.show();
  }

  public static void showConfirmDialog(final Context c, final String title,
      String message, final android.view.View.OnClickListener yes,
      final android.view.View.OnClickListener no) {
    new AlertDialog.Builder(c)
    .setTitle(title)
    .setMessage(message)
    .setIcon(android.R.drawable.ic_dialog_alert)
    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          if(yes != null) yes.onClick(null);
        }})
     .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
         if(no != null) no.onClick(null);
       }}).show();
  }

  public static boolean isMountedSdcard() {
    final String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state))
      return true;
    else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
      return true;
    else
      return false;
  }
  
  @SuppressWarnings("deprecation")
  public static boolean isScreenOn(final Context context) {
    final PowerManager pm = (PowerManager) context
        .getSystemService(Context.POWER_SERVICE);
    return pm.isScreenOn();
  }
  
  public static boolean isServiceRunning(final Context context,
      final Class<?> serviceClass) {
    final ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    for (final RunningServiceInfo service : manager
        .getRunningServices(Integer.MAX_VALUE)) {
      if (serviceClass.getName().equals(service.service.getClassName())) {
        return true;
      }
    }
    return false;
  }

  public static <T extends Activity & IThemeActivity> void switchTo(final T activity, final Class<?> c) {
    switchTo(activity, c, null);
  }

  public static <T extends Activity & IThemeActivity> void switchTo(final T activity, final Class<?> c,
      final Map<String, String> extra) {
    final Intent i = new Intent(activity.getApplicationContext(), c);
    if (extra != null) {
      Set<String> keysSet = extra.keySet();
      for(Iterator<String> keys = keysSet.iterator(); keys.hasNext();) {
        String key = keys.next();
        i.putExtra(key, extra.get(key));
      }
    }
    activity.startActivity(i);
    Fx.updateTransition(activity, true);
  }

  public static <T extends Activity & IThemeActivity> void switchToForResult(final T activity,
      final Class<?> c, final int requestCode) {
    switchToForResult(activity, c, null, requestCode);
  }

  public static <T extends Activity & IThemeActivity> void switchToForResult(final T activity,
      final Class<?> c, final Map<String, String> extra,
      final int requestCode) {
    final Intent i = new Intent(activity.getApplicationContext(), c);
    if (extra != null) {
      Set<String> keysSet = extra.keySet();
      for(Iterator<String> keys = keysSet.iterator(); keys.hasNext();) {
        String key = keys.next();
        i.putExtra(key, extra.get(key));
      }
    }
    activity.startActivityForResult(i, requestCode);
    Fx.updateTransition(activity, true);
  }
  
  public static boolean gainRoot() {
    Process p;
    try {
      // Preform su to get root privledges
      p = Runtime.getRuntime().exec("su");
      
      // Attempt to write a file to a root-only
      DataOutputStream os = new DataOutputStream(p.getOutputStream());
      os.writeBytes("echo \"\" >/system/sd/.root.test\n");
      // Close the terminal
      os.writeBytes("exit\n");
      os.flush();
      try {
        p.waitFor();
        int ex = p.exitValue();
        if (ex != 255) {
          try {
            p = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(p.getOutputStream());
            os.writeBytes("rm -f /system/sd/.root.test\n");
            os.writeBytes("exit\n");
            os.flush();
            p.waitFor();
          } catch (IOException e) {
          }
          return true;
        } else {
          Log.e(TAG, "Errno: " + ex);
          return false;
        }
      } catch (InterruptedException e) {
        Log.e(TAG, "Exception: " + e.getMessage(), e);
        return false;
      }
    } catch (IOException e) {
      Log.e(TAG, "Exception: " + e.getMessage(), e);
      return false;
    }
    
  }
}
