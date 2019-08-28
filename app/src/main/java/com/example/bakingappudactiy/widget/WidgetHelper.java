package com.example.bakingappudactiy.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class WidgetHelper extends IntentService {


    public WidgetHelper() {
        super("WidgetData");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String data=intent.getData().toString();
        Log.d("show1", "onHandleIntent: "+data);
        String title=intent.getStringExtra("title");
        AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(this);
        int []appWidgetIds=appWidgetManager.getAppWidgetIds(new ComponentName(this.getPackageName(), NewAppWidget.class.getName()));
        for (int i=0 ; i<appWidgetIds.length;i++){
            Log.d("show2", "onHandleIntent: "+appWidgetIds[i]);
            NewAppWidget.updateAppWidget(this,appWidgetManager,appWidgetIds[i],data,title);
        }

    }
}
