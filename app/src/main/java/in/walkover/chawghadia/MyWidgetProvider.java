package in.walkover.chawghadia;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Date;


public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";
    int col = 0;
    String[] col_name = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen"};
    int[] wids;


    @Override
    public void onEnabled(Context context) {
        // TODO Auto-generated method stub
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        Date date = new Date();
        // Get all ids
        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            // Create some random data

            int current_time = date.getHours() * 60 + date.getMinutes();
            for (int j = 0; j <= CommonFunction.time.length; j++) {
                if (current_time >= CommonFunction.time[j]) {
                    col++;

                } else
                    break;
            }

            DatabaseHandler db = new DatabaseHandler(context);
            col--;


            String s = db.getSingleValue("" + date.getDay(), "" + col_name[col]);


            //int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    in.walkover.chawghdia.R.layout.widget_layout);


            // Set the text

            remoteViews.setTextViewText(in.walkover.chawghdia.R.id.update, String.valueOf(s));

            // Register an onClickListener
            Intent intent = new Intent(context, MyWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(in.walkover.chawghdia.R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            col = 0;


        }
    }

} 