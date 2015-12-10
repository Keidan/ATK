package org.kei.android.atk.utils.fx;

import org.kei.android.atk.R;

import android.content.Context;

/**
 *******************************************************************************
 * @file Color.java
 * @author Keidan
 * @date 03/12/2015
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
public class Color {
  public static final int BLACK                    = R.color.black;
  public static final int WHITE                    = R.color.white;
  public static final int BLUE_MEDIUM              = R.color.blue_medium;
  public static final int BLUE_DARK                = R.color.blue_dark;
  public static final int ORANGE                   = R.color.orange;
  public static final int RED                      = R.color.red;
  public static final int ORANGE_DARK              = R.color.orange_dark;
  public static final int ORANGE_LIGHT             = R.color.orange_light;
  public static final int GREEN                    = R.color.green;
  public static final int BLUE                     = R.color.blue;
  public static final int GREEN_LIGHT              = R.color.green_light;
  public static final int PURPLE                   = R.color.purple;
  
  public static final int BLUE_TRANSPARENT         = R.color.blue_transparent;
  public static final int ORANGE_TRANSPARENT       = R.color.orange_transparent;
  public static final int PURPLE_TRANSPARENT       = R.color.purple_transparent;
  public static final int GREEN_TRANSPARENT        = R.color.green_transparent;
  public static final int RED_TRANSPARENT          = R.color.red_transparent;
  public static final int BLUE_MEDIUM_TRANSPARENT  = R.color.blue_medium_transparent;
  public static final int BLUE_DARK_TRANSPARENT    = R.color.blue_dark_transparent;
  public static final int ORANGE_DARK_TRANSPARENT  = R.color.orange_dark_transparent;
  public static final int ORANGE_LIGHT_TRANSPARENT = R.color.orange_light_transparent;
  public static final int GREEN_LIGHT_TRANSPARENT  = R.color.green_light_transparent;
  
  private Context         ctx                      = null;

  public static Color newInstance(final Context ctx) {
    return new Color(ctx);
  }

  private Color(final Context ctx) {
    this.ctx = ctx;
  }

  public int getColor(final int id) {
    return ctx.getResources().getColor(id);
  }
}
