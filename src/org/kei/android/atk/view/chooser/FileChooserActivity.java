package org.kei.android.atk.view.chooser;

import java.io.File;

import org.kei.android.atk.R;
import org.kei.android.atk.utils.Tools;
import org.kei.android.atk.view.chooser.handler.ErrorStatus;
import org.kei.android.atk.view.chooser.handler.IProcessHandler;
import org.kei.android.atk.view.chooser.handler.ProcessHandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 *******************************************************************************
 * @file FileChooserActivity.java
 * @author Keidan
 * @date 24/11/2015
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
public class FileChooserActivity extends FileChooser implements IProcessHandler {
  public static final String  FILECHOOSER_SELECTION_KEY            = "selection";
  public static final int     FILECHOOSER_SELECTION_TYPE_FILE      = 1;
  public static final int     FILECHOOSER_SELECTION_TYPE_DIRECTORY = 2;
  private ProcessHandler      handler                              = null;
  private Option              opt                                  = null;

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    handler = new ProcessHandler(this);
  }
  

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    if (item.getItemId() == R.id.action_cancel) {
        cancel();
    }
    return false;
  }
  

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.filechooser_menu, menu);
      return true;
  }

  @Override
  protected void onFileSelected(final Option opt) {
    handler.compute(this, opt);
  }

  @Override
  public void onBackPressed() {
    File parent = currentDir.getParentFile();
    if (parent == null || parent.equals(Tools.DEFAULT_ROOT.getParentFile())) {
      super.onBackPressed();
      cancel();
    } else {
      currentDir = parent;
      fill(currentDir);
    }
  }
  
  private void cancel() {
    finish();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    cancel();
  }

  @Override
  public ErrorStatus doCompute(final Object userObject) {
    opt = (Option) userObject;
    if (opt == null)
      return ErrorStatus.CANCEL; /* cancel action */
    if (!Tools.isMountedSdcard())
      return ErrorStatus.ERROR_NOT_MOUNTED;

    final File file = new File(new File(opt.getPath()).getParent(),
        opt.getName());
    if (!file.canRead())
      return ErrorStatus.ERROR_CANT_READ;
    return ErrorStatus.NO_ERROR;
  }

  @Override
  public void onSuccess() {
    final Intent returnIntent = new Intent();
    int result = RESULT_CANCELED;
    if (opt != null) {
      final File file = new File(new File(opt.getPath()).getParent(),
          opt.getName());
      returnIntent.putExtra(FILECHOOSER_SELECTION_KEY, file.getAbsolutePath());
      if(getUserMessage() != null)
        returnIntent.putExtra(FILECHOOSER_USER_MESSAGE, getUserMessage());
      result = RESULT_OK;
    }
    setResult(result, returnIntent);
    opt = null;
    cancel();
  }

  @Override
  public void onCancel() {

  }

  @Override
  public void onError() {
    opt = null;
    final Intent returnIntent = new Intent();
    setResult(RESULT_CANCELED, returnIntent);
    onBackPressed();
  }
}
