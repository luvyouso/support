package demo.alarmapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends Activity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private RadioButton rbBTMotLan;
    private RadioButton rbBTHangNgay;
    private RadioButton rbBTHangTuan;

    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Read view objects
        LoadSuViews();
    }

    private void LoadSuViews(){
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        rbBTMotLan = (RadioButton) findViewById(R.id.rbBTMotLan);
        rbBTHangNgay = (RadioButton) findViewById(R.id.rbBTHangNgay);
        rbBTHangTuan = (RadioButton) findViewById(R.id.rbBTHangTuan);
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            //Bat bao thuc
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            calendar.set(calendar.SECOND, 0);
            calendar.set(calendar.MILLISECOND, 0);

            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

            if(rbBTMotLan.isChecked()){
                //Bao thuc mot lan
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else if(rbBTHangNgay.isChecked()) {
                //Bao thuc hang ngay
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            } else if(rbBTHangTuan.isChecked()){
                //Bao thuc hang tuan
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*7, pendingIntent);
            }
        } else {
            //Tat bao thuc
            alarmManager.cancel(pendingIntent);
        }
    }
}
