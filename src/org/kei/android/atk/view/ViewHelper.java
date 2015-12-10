package org.kei.android.atk.view;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 *******************************************************************************
 * @file ViewHelper.java
 * @author Keidan
 * @date 24/11/2015
 * @par Project
 * CellHistory
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
public class ViewHelper {
  
  public static void disableScrollViewAutoScrolling(ScrollView view) {
    view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
    view.setFocusable(true);
    view.setFocusableInTouchMode(true);
    view.setOnTouchListener(new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.requestFocusFromTouch();
            return false;
        }
    });
  }
  
  public static void refreshListViewSize(ListView listView) {
      ListAdapter adapter = listView.getAdapter();
      if (adapter == null) {
          //do nothing return null
          return;
      }
      //set listAdapter in loop for getting final size
      int totalHeight = 0;
      for (int size = 0; size < adapter.getCount(); size++) {
          View listItem = adapter.getView(size, null, listView);
          listItem.measure(0, 0);
          totalHeight += listItem.getMeasuredHeight();
      }
    //setting listview item in adapter
      LayoutParams params = listView.getLayoutParams();
      params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
      listView.setLayoutParams(params);
  }
}

