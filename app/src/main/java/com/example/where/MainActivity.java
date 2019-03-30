package com.example.where;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Activity context;
    Button registros;
    Button registrar;
    LocationManager locationManager;
    LocationListener locationListener;
    Location lastLocation;
    DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mDBHelper = new DBHelper(context);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        registrar = (Button) findViewById(R.id.botonRegistrar);
        registros = (Button) findViewById(R.id.botonRegistros);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mDBHelper.addData(location.getLatitude(), location.getLongitude());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Oh fuck");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        //Listener de botones
        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location lastLocationo = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(lastLocationo!= null){
                    mDBHelper.addData(lastLocationo.getLatitude(), lastLocationo.getLongitude());
                }
            }
        });
        registros.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent show = new Intent(context, Log.class);
                startActivity(show);
            }
        });
    }
}
