package de.harryr.openwarn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Log.v("MAIN", "before");
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        LocationListener locListener = new MyLocListener(this);
        try {
            Location location = locationManager.getLastKnownLocation(Context.LOCATION_SERVICE);
            Toast.makeText(this, location.getLatitude() + " " + location.getLongitude(),
                    Toast.LENGTH_LONG).show();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 1, locListener);
        } catch(SecurityException e) {
            // user denied location access
            Log.v("SecEXC", "AAAAAAAAAAAAAAAAAAAAAAAAAndroid l");
        } catch (Exception e) {
            Log.v("EXC", "AAAAAAAAAAAAAAAAAAAAAAAAa");
            Log.v("AAAAAAAAA", e.toString());
        }
        Log.v("MAIN", "after");
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

        // test notification button
        final Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            /**
             * on Click listener for the "add city button"
             * @param v the current view
             */
            public void onClick(View v) {

                /*
                Geocoder gcd = new Geocoder(context, Locale.getDefault());
                List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                }
                else {
                    // do your stuff
                } */
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
