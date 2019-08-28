package com.example.bakingappudactiy.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.bakingappudactiy.MainActivity;
import com.example.bakingappudactiy.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    public static String ing="ingredians";
    public static  String tit="title";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,String ingredians,String title) {

        ing=ingredians;
        tit=title;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.tv_widget_ingredients,ingredians);
        views.setTextViewText(R.id.tv_widget_title,title);
        Intent intent =new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.tv_widget_ingredients,pendingIntent);
        views.setOnClickPendingIntent(R.id.tv_widget_title,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds ) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,ing,tit);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
