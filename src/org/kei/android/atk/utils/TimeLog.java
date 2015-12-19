package org.kei.android.atk.utils;

import java.util.Date;

import android.util.Log;

/**
 *******************************************************************************
 * @file TimeLog.java
 * @author Keidan
 * @date 19/12/2015
 * @par Project CellHistory
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
public class TimeLog {
  private long counter   = 0;
  private long startTime = 0;
  private long stopTime  = 0;

  public void start(int n) {
    counter = 0;
    startTime = new Date().getTime();
  }

  public void update() {
    stopTime = new Date().getTime();
    counter++;
  }

  public long getEstimated() {
    return counter * 1000;
  }

  public long getReal() {
    return stopTime - startTime;
  }

  public void print(Class<?> owner) {
    final Date estimated = new Date(getEstimated());
    final Date real = new Date(getReal());
    final String name = owner.getSimpleName();
    Log.i(name, name + " -> Start time: " + new Date(startTime));
    Log.i(name, name + " -> Real time: " + real);
    Log.i(name, name + " -> Estimated time: " + estimated);
  }
  
  public long getCounter() {
    return counter;
  }
  
  public long getStartTime() {
    return startTime;
  }
  
  public long getStopTime() {
    return stopTime;
  }
  
}

