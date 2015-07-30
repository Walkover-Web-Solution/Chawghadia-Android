package in.walkover.chawghadia;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Collator;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import in.walkover.chawghdia.R;


public class MainActivity extends Activity {

    String[] row1 = new String[17];
    Context ctx = this;
    Cursor c = null;
    GetTextClass gt;
    int col = 0;
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            date.setYear(year - 1900);
            date.setMonth(monthOfYear);
            date.setDate(dayOfMonth);
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            int day = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;
            Cursor s = db.getAllValues("" + day);
            if (s.moveToFirst()) {
                int i;
                for (i = 0; i < s.getColumnCount(); i++) {
                    row1[i] = "" + s.getString(i);
                }
                tempL.setEnabled(true);
                tempR.setEnabled(true);
                tempview.setText(row1[col]);
                tv2.setText(CommonFunction.time_duration[col]);
            }
            updateLable();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stubf
            date.setHours(hourOfDay);
            date.setMinutes(minute);
            myCalendar.set(Calendar.HOUR, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            int current_time = myCalendar.get(Calendar.HOUR_OF_DAY) * 60 + myCalendar.get(Calendar.MINUTE);
            int p = 0;
            for (int j = 0; j <= CommonFunction.time.length; j++) {
                if (current_time >= CommonFunction.time[j]) {
                    p++;

                } else
                    break;
            }
            col = p;
            tv2.setText(CommonFunction.time_duration[col]);


            tempview.setText(row1[col]);
            updateLable();
        }
    };
    int current_col;
    Date date = new Date();
    DatabaseHandler db = new DatabaseHandler(this);
    DateFormat fmtDate = DateFormat.getDateInstance();
    DateFormat fmtTime = DateFormat.getTimeInstance();
    TextView tv2;
    Typeface typeface;
    SharedPreferences sharedPreferences;
    Button Lbutton, Rbutton, ViewChawgharia, tv1, tempR, tempL, tempview, showdate, showtime;
    int current_time;
    DateClass dategetter = new DateClass();
    Calendar myCalendar = Calendar.getInstance();

    private void updateLable() {
        showdate.setText(fmtDate.format(myCalendar.getTime()));
        showtime.setText(fmtTime.format(myCalendar.getTime()));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/fonts.TTF");
        boolean ServiceAns = isMyServiceRunning("ServiceName with pkg. (com.example.newproject.ServiceForUpdation)");
        if (ServiceAns == false) {
            Intent i = new Intent(this, ServiceForUpdation.class);
            this.startService(i);
        }
        sharedPreferences = getSharedPreferences(CommonFunction.PrefrenceFile, MODE_PRIVATE);
        String checkData = sharedPreferences.getString(CommonFunction.DataCheck, "false");
        gt = new GetTextClass();

        if ("false".equalsIgnoreCase(checkData)) {
            db.CreateDatabase(this);

        }
        c = db.getAllValues();

        tempL = (Button) findViewById(R.id.TbtnL);
        tempR = (Button) findViewById(R.id.TbtnR);
        tempview = (Button) findViewById(R.id.Ttxt1);
        tempview.setTypeface(typeface);
        tv2 = (TextView) findViewById(R.id.txt2);
        tv2.setTypeface(typeface);
        showdate = (Button) findViewById(R.id.showdate);
        showtime = (Button) findViewById(R.id.showtime);
        showdate.setText(fmtDate.format(date));
        showtime.setText(fmtTime.format(date));
        ViewChawgharia = (Button) findViewById(R.id.viewchawghariya);
        ViewChawgharia.setTypeface(typeface);
        showdate.setTypeface(typeface);
        showtime.setTypeface(typeface);

        Intent start = new Intent(MainActivity.this, UpdateTimeService.class);
        startService(start);
        row1 = getData(ctx, db);

        current_time = date.getHours() * 60 + date.getMinutes();

        for (int j = 0; j <= CommonFunction.time.length; j++) {
            if (current_time >= CommonFunction.time[j]) {

                col++;
                if (col == 17) {
                    col = 16;
                    break;
                }
            } else
                break;
        }
        current_col = col;
        tempview.setText(gt.getCurrentString(MainActivity.this, date, col));


        tv2.setText(CommonFunction.time_duration[col]);


        tempview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                date = new Date();
                col = current_col;
                tempview.setText(gt.getCurrentString(MainActivity.this, date, col));
                showdate.setText(fmtDate.format(date));
                showtime.setText(fmtTime.format(date));


                tv2.setText(CommonFunction.time_duration[col]);

            }
        });
        tempR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                col++;
                //Log.i("Text Check4.1",""+col);

                if (col == 17) {

                    //Log.i("Text Check4.1",""+date.getYear());

                    col = 1;
                }
                if (col == 5) {
                    date = dategetter.getNextDate(date);
                }

                showdate.setText(fmtDate.format(date));
                tempview.setText(gt.getNextString(MainActivity.this, date, col));
                tv2.setText(CommonFunction.time_duration[col]);


            }
        });
        tempL.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                col--;

                if (col == 0) {
                    col = 16;


                }
                if (col == 4) {
                    date = dategetter.previousDate(date);
                }

                showdate.setText(fmtDate.format(date));
                tempview.setText(gt.getPreviousString(MainActivity.this, date, col));
                tv2.setText(CommonFunction.time_duration[col]);
            }
        });
        showtime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, t, myCalendar
                        .get(Calendar.HOUR_OF_DAY), myCalendar
                        .get(Calendar.MINUTE), true).show();

            }
        });
        showdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showtime.setEnabled(true);
                new DatePickerDialog(MainActivity.this, d, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        //updateLable();

        showtime.setEnabled(false);


        ViewChawgharia.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i;
                Toast.makeText(MainActivity.this, "WELCOME", Toast.LENGTH_LONG).show();
                String ampm = new String();

                Calendar c = new GregorianCalendar();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                if (c.get(Calendar.AM_PM) == 0) {
                    ampm = "AM";
                } else {
                    ampm = "PM";
                }
                String time = "" + hour + ":" + minute + "" + ampm;

                Collator myCollator = Collator.getInstance();


                if ((myCollator.compare(time, "6.00 AM") < 0)
                        && (myCollator.compare(time, "6.00 PM") < 0)) {

                    i = new Intent(MainActivity.this, NightView.class);
                } else {


                    i = new Intent(MainActivity.this, DayView.class);
                }
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                startActivity(i);

            }
        });
    }


    // getting complete row according to the day
    String[] getData(Context ctx, DatabaseHandler db) {
        String[] row = new String[17];
        date.getDay();
        // getting row
        Cursor c = db.getAllValues("" + date.getDay());
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                row[i] = "" + c.getString(i);

            }
        }
        return row;

    }


    private boolean isMyServiceRunning(String ServicePakage) {
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ServicePakage.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}
