package org.kei.android.atk.view.dialog;

import org.kei.android.atk.R;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 *******************************************************************************
 * @file DialogBuilder.java
 * @author Keidan
 * @date 25/12/2015
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
public class DialogBuilder extends AlertDialog.Builder {
  
  private View      dialogView = null;
  private TextView  title      = null;
  private View      divider    = null;
  
  public DialogBuilder(final Context context) {
    super(context);
    
    dialogView = View.inflate(context, R.layout.dialog_layout, null);
    setView(dialogView);
    
    title = (TextView) dialogView.findViewById(R.id.alertTitle);
    divider = dialogView.findViewById(R.id.divider);
  }
  
  public View getView() {
    return dialogView;
  }
  
  public DialogBuilder setDividerColor(final String colorString) {
    divider.setBackgroundColor(Color.parseColor(colorString));
    return this;
  }
  
  public DialogBuilder setDividerColor(final int color) {
    divider.setBackgroundColor(color);
    return this;
  }
  
  @Override
  public DialogBuilder setTitle(final CharSequence text) {
    title.setText(text);
    return this;
  }
  
  public DialogBuilder setTitleColor(final String colorString) {
    title.setTextColor(Color.parseColor(colorString));
    return this;
  }
  
  public DialogBuilder setTitleColor(final int color) {
    title.setTextColor(color);
    return this;
  }
  
  public DialogBuilder setCustomView(final int resId, final Context context) {
    final View customView = View.inflate(context, resId, null);
    ((FrameLayout) this.dialogView.findViewById(R.id.content))
        .addView(customView);
    return this;
  }
  
  @Override
  public AlertDialog show() {
    if (title.getText().equals(""))
      dialogView.findViewById(R.id.top).setVisibility(View.GONE);
    return super.show();
  }
}
