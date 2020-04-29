package de.harryr.openwarn;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocListener implements LocationListener {
    private Context context;

    MyLocListener(Context context) {
        super();
        this.context = context.getApplicationContext();
        Log.v("LOC", "started");
    }

    @Override
    public void onLocationChanged(Location loc) {
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v("LOC", longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v("LOC", latitude);
        Toast.makeText(context, latitude + " " + longitude,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
