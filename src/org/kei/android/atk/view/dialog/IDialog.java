package org.kei.android.atk.view.dialog;


import android.view.View;

/**
 *******************************************************************************
 * @file IDialog.java
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
public interface IDialog {
  
  public DialogResult doAction(View owner, Object model);
  
  public void doLoad(View owner, Object model);
}
