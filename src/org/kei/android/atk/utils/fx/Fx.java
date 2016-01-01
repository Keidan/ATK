package org.kei.android.atk.utils.fx;

import java.lang.reflect.Method;

import org.kei.android.atk.R;
import org.kei.android.atk.utils.Tools;
import org.kei.android.atk.view.IThemeActivity;
import org.kei.android.atk.view.IThemeActivity.AnimationType;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 *******************************************************************************
 * @file Fx.java
 * @author Keidan
 * @date 10/09/2015
 * @par Project ATK
 *
 * @par Copyright 2015 Keidan, all right reserved
 *
 *      This software is distributed in the hope that it will be useful, but
 *      WITHOUT ANY WARRANTY.
 *
 *      License summary : You can modify and redistribute the sources code and
 *      binaries. You can send me the bug-fix
 *
 *      Term of the license in in the file license.txt.
 *
 *******************************************************************************
 */
public class Fx {

  public static final String KEY_ANIMATIONS                = "atk_key_animations";
  public static final String KEY_THEMES                    = "atk_key_themes";
  public static final String ANIMATION_SLIDE_RIGHT_TO_LEFT = "org.kei.android.atk.utils.fx.ANIMATION_SLIDE_RIGHT_TO_LEFT";
  public static final String ANIMATION_SLIDE_LEFT_TO_RIGHT = "org.kei.android.atk.utils.fx.ANIMATION_SLIDE_LEFT_TO_RIGHT";
  public static final String ANIMATION_FADE                = "org.kei.android.atk.utils.fx.ANIMATION_FADE";
  public static final String THEME_LIGHT                   = "org.kei.android.atk.utils.fx.THEME_LIGHT";
  public static final String THEME_DARK                    = "org.kei.android.atk.utils.fx.THEME_DARK";
  public static String       default_animation             = ANIMATION_FADE;
  public static String       default_theme                 = ANIMATION_FADE;

  @SuppressWarnings("deprecation")
  public static Drawable getDrawable(final Context owner, final int id) {
    return owner.getResources().getDrawable(id);
  }

  public static Drawable resize(final Context context, final Drawable image,
      final int width, final int height) {
    final Bitmap b = ((BitmapDrawable) image).getBitmap();
    final Bitmap bitmapResized = Bitmap.createScaledBitmap(b, width, height,
        false);
    return new BitmapDrawable(context.getResources(), bitmapResized);
  }

  public static <T extends Activity & IThemeActivity> int getAnimationFromPref(
      final T owner, final AnimationType at) {
    final String value = Tools.getPrefString(owner, KEY_ANIMATIONS,
        default_animation);
    if (value.equals(ANIMATION_SLIDE_LEFT_TO_RIGHT)) {
      if (at == AnimationType.ENTER_IN)
        return R.anim.slide_leave_in;
      else if (at == AnimationType.ENTER_OUT)
        return R.anim.slide_leave_out;
      else if (at == AnimationType.LEAVE_IN)
        return R.anim.slide_enter_in;
      else
        return R.anim.slide_enter_out;
    } else if (value.equals(ANIMATION_SLIDE_RIGHT_TO_LEFT)) {
      if (at == AnimationType.ENTER_IN)
        return R.anim.slide_enter_in;
      else if (at == AnimationType.ENTER_OUT)
        return R.anim.slide_enter_out;
      else if (at == AnimationType.LEAVE_IN)
        return R.anim.slide_leave_in;
      else
        return R.anim.slide_leave_out;
    } else if (value.equals(ANIMATION_FADE)) {
      if (at == AnimationType.ENTER_IN)
        return R.anim.fade_in;
      else if (at == AnimationType.ENTER_OUT)
        return R.anim.fade_out;
      else if (at == AnimationType.LEAVE_IN)
        return R.anim.fade_in;
      else
        return R.anim.fade_out;
    }
    return 0;
  }
  
  public static <T extends Activity & IThemeActivity> void updateTransition(
      final T owner, final boolean in) {
    if (in)
      owner.overridePendingTransition(owner.getAnime(AnimationType.ENTER_IN),
          owner.getAnime(AnimationType.ENTER_OUT));
    else
      owner.overridePendingTransition(owner.getAnime(AnimationType.LEAVE_IN),
          owner.getAnime(AnimationType.LEAVE_OUT));
  }

  public static boolean switchThemeFromPref(final Activity owner) {
    final String value = Tools.getPrefString(owner.getBaseContext(),
        KEY_THEMES, default_theme);
    if (value.equals(THEME_DARK)) {
      switchTheme(owner, R.style.AppBaseThemeDark, false);
      return true;
    } else if (value.equals(THEME_LIGHT)) {
      switchTheme(owner, R.style.AppBaseThemeLight, false);
      return true;
    }
    return false;
  }

  public static void switchTheme(final Activity a, final int theme,
      final boolean restart) {
    a.setTheme(theme);
    if (restart)
      Tools.restartApplication(a);
  }
  
  public static void setVisibilityAnimation(final View v, final int visibility,
      final int animId) {
    final Animation anim = AnimationUtils.loadAnimation(v.getContext()
        .getApplicationContext(), animId);
    v.setAnimation(anim);
    v.setVisibility(visibility);
  }
  
  public static void setVisibilityAnimationSync(final View owner, final View v,
      final int visibility, final int animId) {
    owner.setEnabled(false);
    final Animation anim = AnimationUtils.loadAnimation(v.getContext()
        .getApplicationContext(), animId);
    anim.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(final Animation arg0) {
      }

      @Override
      public void onAnimationRepeat(final Animation arg0) {
      }

      @Override
      public void onAnimationEnd(final Animation arg0) {
        owner.setEnabled(true);
      }
    });
    v.setAnimation(anim);
    v.setVisibility(visibility);
  }

  public static int getCurrentThemeId(Context context) {
    try {
      final Class<?> wrapper = Context.class;
      final Method method = wrapper.getMethod("getThemeResId");
      method.setAccessible(true);
      return (Integer) method.invoke(context);
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}
