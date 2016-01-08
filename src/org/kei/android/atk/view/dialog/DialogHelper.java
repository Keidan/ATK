package org.kei.android.atk.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 *******************************************************************************
 * @file DialogHelper.java
 * @author Keidan
 * @date 08/01/2016
 * @par Project ATK
 *
 * @par Copyright 2016 Keidan, all right reserved
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
public class DialogHelper {
  
  
  public static void showCustomDialog(final Context owner, final int layout,
      final String title, final int titleColor, final IDialog action, final Object model, final int idOk, final int idCancel) {
    final DialogBuilder dialogBuilder = new DialogBuilder(owner).
        setTitle(title).
        setTitleColor(titleColor).
        setDividerColor(titleColor).
        setCustomView(layout, owner);
    final Dialog dialog = dialogBuilder.create();
    
    final Button dialogButtonOk = (Button) dialogBuilder.getView().findViewById(idOk);
    final Button dialogButtonCancel = (Button) dialogBuilder.getView().findViewById(idCancel);
    final OnClickListener click = new OnClickListener() {
      @Override
      public void onClick(final View v) {
        if (v.equals(dialogButtonOk)) {
          if (action.doAction(dialogBuilder.getView(), model) == DialogResult.SUCCESS)
            dialog.dismiss();
        } else
          dialog.dismiss();
      }
    };
    dialogButtonOk.setOnClickListener(click);
    dialogButtonCancel.setOnClickListener(click);
    action.doLoad(dialogBuilder.getView(), model);
    dialog.show();
  }
  
  
}
