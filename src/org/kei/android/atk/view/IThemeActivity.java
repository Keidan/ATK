package org.kei.android.atk.view;

/**
 *******************************************************************************
 * @file IThemeActivity.java
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
public interface IThemeActivity {
  public enum AnimationType {
    ENTER_IN,
    ENTER_OUT,
    LEAVE_IN,
    LEAVE_OUT
  };
  
  public void themeUpdate();
  
  public int getAnime(AnimationType at);
}
