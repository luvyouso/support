package demo.alarmapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by Lam Phong on 5/20/2016.
 */
public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification();
    }

    private void sendNotification() {

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100,
                new Intent(this, MainActivity.class), 0);
        Notification noti = new Notification.Builder(this)
                .setTicker("Đã đến giờ thức dậy!!!")
                .setContentTitle("Đã đến giờ thức dậy!!!")
                .setContentText("Chúc bạn có một ngày thật vui vẻ")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(alarmUri)
                .setContentIntent(pendingIntent).getNotification();

        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }
}