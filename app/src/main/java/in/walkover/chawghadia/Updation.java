package in.walkover.chawghadia;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.Date;


public class Updation extends Service {
    String atTime = new String();
    int col = 0;


    String[] col_name = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen"};
    Date date = new Date();

    String cvalue = new String();
    String nvalue = new String();

    @Override

    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub

        //Calendar cal=Calendar.getInstance();
        //cal.set(null, null, 26);
        //Calendar.set(Calendar.HOUR_OF_DAY, 23) ;


        super.onStart(intent, startId);
        DatabaseHandler db = new DatabaseHandler(this);
        int current_time = date.getHours() * 60 + date.getMinutes();

        for (int j = 0; j < CommonFunction.time.length; j++) {
            if (current_time > CommonFunction.time[j]) {
                col++;

                if (col == 17) {
                    col = 16;
                    break;
                }
            } else
                break;
        }

        ServiceForUpdation.share = (CommonFunction.time[col] - current_time);

        if (col == 16) {
            if (date.getDay() == 6) {

                cvalue = db.getSingleValue("" + date.getDay(), "" + col_name[col - 1]);
                nvalue = db.getSingleValue("0", "one");
            } else {

                cvalue = db.getSingleValue("" + date.getDay(), "" + col_name[col - 1]);


                nvalue = db.getSingleValue("" + ((date.getDay()) + 1), "one");
            }

        } else {

            cvalue = db.getSingleValue("" + date.getDay(), "" + col_name[col - 1]);
            nvalue = db.getSingleValue("" + date.getDay(), "" + col_name[col]);
        }
        onBind(intent);
    }


    @Override
    public IBinder onBind(Intent intent) {
        if (ServiceForUpdation.share > 60) {
            atTime = "" + ServiceForUpdation.share / 60 + " Hrs " + "" + ServiceForUpdation.share % 60 + "Min";

        } else {
            atTime = " " + ServiceForUpdation.share + " Min";
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(in.walkover.chawghdia.R.drawable.logo_150, cvalue + "  ", System.currentTimeMillis());


        // Hide the notification after its selected
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent activity = PendingIntent.getActivity(this, 0, i, 0);
        notification.setLatestEventInfo(this, "" + cvalue, "Coming Next  ::: " + nvalue + " In " + atTime, activity);

        //notification.number += 1;
        notificationManager.notify(0, notification);

        Context context = this;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), in.walkover.chawghdia.R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        remoteViews.setTextViewText(in.walkover.chawghdia.R.id.update, "" + cvalue);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        stopService(intent);
        return null;
    }

    @Override
    public boolean stopService(Intent name) {

        return super.stopService(name);
    }

}
