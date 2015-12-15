package org.kei.android.atk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.widget.RemoteViews;

/**
 *******************************************************************************
 * @file NotificationHelper.java
 * @author Keidan
 * @date 15/12/2015
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
public class NotificationHelper {
  private Notification        notify              = null;
  private Context             c                   = null;
  private int                 nfyId               = 0;
  private boolean             sound               = false;
  private boolean             vibration           = false;
  private NotificationManager notificationManager = null;

  public NotificationHelper(final Context c, final int nfyId) {
    this.c = c;
    this.nfyId = nfyId;
    notificationManager = (NotificationManager) c
        .getSystemService(Context.NOTIFICATION_SERVICE);
  }

  public void setExtra(final boolean vibration, final boolean sound) {
    this.sound = sound;
    this.vibration = vibration;
  }

  public void hide() {
    if (notificationManager == null)
      return;
    notificationManager.cancel(nfyId);
    notify = null;
    notificationManager = null;
  }

  public void update(final String message) {
    update(null, message);
  }

  public void update(final String title, final String message) {
    if (notificationManager == null) return;
    final RemoteViews contentView = new RemoteViews(c.getPackageName(),
        org.kei.android.atk.R.layout.notifications);
    boolean change = false;
    if (title != null) {
      contentView.setTextViewText(org.kei.android.atk.R.id.title,
          title);
      change = true;
    }
    if (message != null) {
      contentView.setTextViewText(org.kei.android.atk.R.id.text,
          message);
      change = true;
    }
    if (change) {
      notify.contentView = contentView;
      notificationManager.notify(nfyId, notify);
    }
  }

  @SuppressWarnings("deprecation")
  public void show(final int icon, final String ticker, final String title,
      final String message, final PendingIntent contentIntent) {
    final long when = System.currentTimeMillis();
    notify = new Notification(icon, ticker, when);
    final RemoteViews contentView = new RemoteViews(c.getPackageName(),
        org.kei.android.atk.R.layout.notifications);
    // Locate and set the Image into customnotificationtext.xml ImageViews
    contentView.setImageViewResource(org.kei.android.atk.R.id.imagenotileft, icon);
    // Locate and set the Text into customnotificationtext.xml TextViews
    contentView.setTextViewText(org.kei.android.atk.R.id.title, title);
    contentView.setTextViewText(org.kei.android.atk.R.id.text, message);
    
    
    notify.contentView = contentView;
    notify.contentIntent = contentIntent;
    // Do not clear the notification
    notify.flags |= Notification.FLAG_NO_CLEAR;
    notify.defaults |= Notification.DEFAULT_LIGHTS; // LED
    if (vibration)
      notify.defaults |= Notification.DEFAULT_VIBRATE; // Vibration
    if (sound)
      notify.defaults |= Notification.DEFAULT_SOUND; // Sound
    notificationManager.notify(nfyId, notify);
  }
}
