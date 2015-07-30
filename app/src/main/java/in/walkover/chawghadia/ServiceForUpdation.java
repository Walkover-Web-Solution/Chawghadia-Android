package in.walkover.chawghadia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Date;


public class ServiceForUpdation extends Service {

    //int[] time={0,90,180,270,360,450,540,630,720,810,900,990,1080,1170,1260 ,1350};

    static int share;
    int[] time = {0, 90, 180, 270, 360, 450, 540, 630, 720, 810, 900, 990,
            1080, 1170, 1260, 1350, 1440};
    Date date = new Date();
    Calendar cal = Calendar.getInstance();

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        onBind(intent);
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub

        int current_time = date.getHours() * 60 + date.getMinutes() + date.getSeconds() / 60;
        int i = 0;

        while (current_time >= time[i]) {
            i++;


            if (i == 17) {
                i = 16;
                break;
            }

        }
        int updation_time = time[i] - current_time;
        share = updation_time;

        cal.add(Calendar.MINUTE, 1);

        Intent inte = new Intent(this, Updation.class);
        //Log.i("i m calling service","hello");
        PendingIntent pendingIntent0 = PendingIntent.getService(this, 0, inte, 0);
        AlarmManager alarmMgr0 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        alarmMgr0.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 2 * 60 * 1000, pendingIntent0);
        return null;
    }

}
