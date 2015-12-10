package org.kei.android.atk.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 *******************************************************************************
 * @file ViewsCrossFader.java
 * @author Keidan
 * @date 06/12/2015
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
public class ViewsCrossFader {
  private final View viewToFadeOut;
  private final View viewToFadeIn;
  private final int  fadeDuration;
  
  /***
   * Instantiate a new CrossFader object.
   * 
   * @param viewToFadeOut
   *          the view to fade out
   * @param viewToFadeIn
   *          the view to fade in
   * @param fadeDuration
   *          the duration in milliseconds for each fade to last
   */
  public ViewsCrossFader(final View viewToFadeOut, final View viewToFadeIn,
      final int fadeDuration) {
    this.viewToFadeOut = viewToFadeOut;
    this.viewToFadeIn = viewToFadeIn;
    this.fadeDuration = fadeDuration;
  }
  
  /***
   * Start the cross-fade animation.
   */
  public void apply() {
    viewToFadeIn.setAlpha(0f);
    viewToFadeIn.setVisibility(View.VISIBLE);
    viewToFadeOut.animate().alpha(0f).setDuration(fadeDuration)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(final Animator animation) {
            viewToFadeOut.setVisibility(View.GONE);
            viewToFadeIn.animate().alpha(1f).setDuration(fadeDuration)
                .setListener(null);
          }
        });
  }
}
