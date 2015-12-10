package org.kei.android.atk.view;

import org.kei.android.atk.R;
import org.kei.android.atk.utils.Tools;
import org.kei.android.atk.utils.fx.Fx;

import android.app.ListActivity;
import android.os.Bundle;

/**
 *******************************************************************************
 * @file EffectListActivity.java
 * @author Keidan
 * @date 26/11/2015
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
public class EffectListActivity extends ListActivity implements IThemeActivity {
  private static final int BACK_TIME_DELAY = 2000;
  private static long      lastBackPressed = -1;
  

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    themeUpdate();
    super.onCreate(savedInstanceState);
    Fx.updateTransition(this, true);
  }

  @Override
  protected void onPause() {
    super.onPause();
    Fx.updateTransition(this, false);
  }
  
  @Override
  public void onBackPressed() {
    if(!exitOnDoubleBack()) {
      super.onBackPressed();
    } else {
      if (lastBackPressed + BACK_TIME_DELAY > System.currentTimeMillis()) {
          super.onBackPressed();
      } else {
        Tools.toast(getBaseContext(), getToastIconId(), getResources().getText(getOnDoubleBackExitTextId()));
      }
      lastBackPressed = System.currentTimeMillis();
    }
  }
  
  protected boolean exitOnDoubleBack() {
    return true;
  }
  
  protected int getToastIconId() {
    return R.drawable.ic_launcher;
  }
  
  protected int getOnDoubleBackExitTextId() {
    return R.string.onDoubleBackExitText;
  }

  @Override
  public void themeUpdate() {
  }
  
  @Override
  public int getAnime(AnimationType at) {
    return Fx.getAnimationFromPref(this, at);
  }
}
