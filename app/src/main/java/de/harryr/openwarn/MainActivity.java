package de.harryr.openwarn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        // test notification button
        final Button button = findViewById(R.id.btn_notify);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * on Click listener for the test notification button. Just creates and sows a test notification
             * @param v the current view
             */
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "42")
                        .setContentTitle("title")
                        .setContentText("text")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(42, builder.build());
            }
        });

    }

    /**
     * Creates a notification channel, which is needed to send notifications to Android 8 and above
     * See: https://developer.android.com/training/notify-user/build-notification#java
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String ch_name = getString(R.string.channel_name);
            String ch_desc = getString(R.string.channel_description);
            int ch_importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("42", ch_name, ch_importance);
            channel.setDescription(ch_desc);
            // register to system, changes not possible anymore
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
