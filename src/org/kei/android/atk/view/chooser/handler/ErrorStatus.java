package org.kei.android.atk.view.chooser.handler;

/**
 *******************************************************************************
 * @file ErrorStatus.java
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
public enum ErrorStatus {
  NO_ERROR, CANCEL, ERROR_NOT_MOUNTED, ERROR_CANT_READ, ERROR_NOT_FOUND, ERROR_INVALID_FORMAT, ERROR_INVALID_VALUE;

  String message = null;

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
};
