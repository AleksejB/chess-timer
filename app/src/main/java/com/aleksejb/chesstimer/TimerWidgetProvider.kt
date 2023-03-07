package com.aleksejb.chesstimer

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.aleksejb.domain.core.timer.constants.Constants
import com.aleksejb.domain.core.timer.util.toTimerTime
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class TimerWidgetProvider: AppWidgetProvider() {

    private val ACTION_OPEN_CHESS = "ACTION_OPEN_CHESS"

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach {
            updateAppWidget(context, appWidgetManager, it)
        }
    }

//    override fun onReceive(context: Context?, intent: Intent?) {
//        super.onReceive(context, intent)
//
//        if (context == null) return
//
//        val views = RemoteViews(context.packageName, R.layout.timer_widget_layout)
//        val appWidget = ComponentName(context, TimerWidgetProvider::class.java)
//        val appWidgetManager = AppWidgetManager.getInstance(context)
//
//        appWidgetManager.updateAppWidget(appWidget, views)
//    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun updateAppWidget(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int
    ) {
        if (context == null) return

        val views = RemoteViews(context.packageName, R.layout.timer_widget_layout)

        val startChessIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chess.com/"))
        val startChessPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, 0, startChessIntent,
                0 or PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, startChessIntent, 0)
        }

        val openAppIntent = Intent(context, MainActivity::class.java)
        val openAppPendongIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, 0, openAppIntent,
                0 or PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, openAppIntent, 0)
        }

        views.setOnClickPendingIntent(R.id.openChessTV, startChessPendingIntent)
        views.setOnClickPendingIntent(R.id.openAppTV, openAppPendongIntent)

        appWidgetManager?.updateAppWidget(appWidgetId, views)
    }
}


//having onReceive affects if button clicks are handled
