package com.example.projekt1_smb2

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)
        views.setTextViewText(R.id.appwidget_text, context.getString(R.string.appwidget_text))
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, 1, views)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == context?.getString(R.string.actionBt)) {
            var imageId = intent?.getIntExtra("imgId", 1)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews = RemoteViews(context!!.packageName, R.layout.new_app_widget)
            val appWidget = ComponentName(context, NewAppWidget::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(appWidget)

            if (imageId == 3) {
                imageId = imageId?.minus(2)
                val newImageId = context.resources.getIdentifier("img$imageId", "drawable", context.packageName)
                remoteViews.setImageViewResource(R.id.imageView, newImageId)
                appWidgetManager.updateAppWidget(appWidget, remoteViews)
                for (appWidgetId in appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, imageId!!, remoteViews)
                }
            } else {
                imageId = imageId?.plus(1)
                val newImageId = context.resources.getIdentifier("img$imageId", "drawable", context.packageName)
                remoteViews.setImageViewResource(R.id.imageView, newImageId)
                appWidgetManager.updateAppWidget(appWidget, remoteViews)
                for (appWidgetId in appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, imageId!!, remoteViews)
                }
            }
        }
        if (intent?.action == context?.getString(R.string.actionBtPrevious)) {
            val broadcastIntent = Intent(context?.getString(R.string.broadcast_test))
            broadcastIntent.putExtra("button", intent?.action)
            context?.sendBroadcast(broadcastIntent)
        }
        if (intent?.action == context?.getString(R.string.actionBtPlay)) {
            val broadcastIntent = Intent(context?.getString(R.string.broadcast_test))
            broadcastIntent.putExtra("button", intent?.action)
            context?.sendBroadcast(broadcastIntent)
        }
        if (intent?.action == context?.getString(R.string.actionBtPause)) {
            val broadcastIntent = Intent(context?.getString(R.string.broadcast_test))
            broadcastIntent.putExtra("button", intent?.action)
            context?.sendBroadcast(broadcastIntent)
        }
        if (intent?.action == context?.getString(R.string.actionBtStop)) {
            val broadcastIntent = Intent(context?.getString(R.string.broadcast_test))
            broadcastIntent.putExtra("button", intent?.action)
            context?.sendBroadcast(broadcastIntent)
        }
        if (intent?.action == context?.getString(R.string.actionBtNext)) {
            val broadcastIntent = Intent(context?.getString(R.string.broadcast_test))
            broadcastIntent.putExtra("button", intent?.action)
            context?.sendBroadcast(broadcastIntent)
        }
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int, imgId: Int, views: RemoteViews) {
    var requestCode = 0
//    val widgetText = context.getString(R.string.appwidget_text)
//    // Construct the RemoteViews object
//    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)

    val intentWWW = Intent(Intent.ACTION_VIEW)
    intentWWW.data = Uri.parse("https://www.pja.edu.pl")
    val pendingIntentWWW = PendingIntent.getActivity(
            context,
            requestCode++,
            intentWWW,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.button2, pendingIntentWWW)

    val intentImage = Intent(context?.getString(R.string.actionBt))
    intentImage.component = ComponentName(context, NewAppWidget::class.java)
    intentImage.putExtra("imgId", imgId)
    val pendingIntentImage = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentImage,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.button, pendingIntentImage)

    val intentMusicPrev = Intent(context?.getString(R.string.actionBtPrevious))
    intentMusicPrev.component = ComponentName(context, NewAppWidget::class.java)
    val pendingIntentMusicPrev = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentMusicPrev,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.buttonPrevious, pendingIntentMusicPrev)

    val intentMusicPlay = Intent(context?.getString(R.string.actionBtPlay))
    intentMusicPlay.component = ComponentName(context, NewAppWidget::class.java)
    val pendingIntentMusicPlay = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentMusicPlay,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.buttonPlay, pendingIntentMusicPlay)

    val intentMusicPause = Intent(context?.getString(R.string.actionBtPause))
    intentMusicPause.component = ComponentName(context, NewAppWidget::class.java)
    val pendingIntentMusicPause = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentMusicPause,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.buttonPause, pendingIntentMusicPause)

    val intentMusicStop = Intent(context?.getString(R.string.actionBtStop))
    intentMusicStop.component = ComponentName(context, NewAppWidget::class.java)
    val pendingIntentMusicStop = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentMusicStop,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.buttonStop, pendingIntentMusicStop)

    val intentMusicNext = Intent(context?.getString(R.string.actionBtNext))
    intentMusicNext.component = ComponentName(context, NewAppWidget::class.java)
    val pendingIntentMusicNext = PendingIntent.getBroadcast(
            context,
            requestCode++,
            intentMusicNext,
            PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.buttonNext, pendingIntentMusicNext)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}