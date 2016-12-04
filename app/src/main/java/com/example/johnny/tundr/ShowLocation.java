package com.example.johnny.tundr;
//45.3840977, -75.6983455

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

public class ShowLocation extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    protected final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    private GoogleApiClient mGoogleApiClient;
    String mLastUpdateTime;
    Location mLastLocation;

    LocationRequest mLocationRequest;
    TextView latitude;
    TextView longitude;
    TextView time;
    Button getUpdate;
    boolean mRequestingLocationUpdates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        latitude = (TextView) findViewById(R.id.Latitude);
        longitude = (TextView) findViewById(R.id.Longitude);
        time = (TextView) findViewById(R.id.time);

        mRequestingLocationUpdates = false;
        updateValuesFromBundle(savedInstanceState);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            createLocationRequest();

        }

    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        Log.i("location-updates-sample", "Updating values from bundle");
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
            }
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                mLastLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        if (mLastLocation == null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            //updateUI();
        }

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void GetLocation(View view) {
        /*
        latitude = (TextView) findViewById(R.id.Latitude);
        longitude = (TextView) findViewById(R.id.Longitude);
        time = (TextView) findViewById(R.id.time);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            updateUI();
        }
        createLocationRequest();*/
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            //setButtonsEnabledState();
            startLocationUpdates();
        }
        /*if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            setButtonsEnabledState();
            stopLocationUpdates();
        }*/
    }
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }
    private void updateUI() {

        Log.d("Latitude: ",String.valueOf(latitude==null));
        Log.d("Longitude: ",String.valueOf(longitude==null));
        Log.d("time: ",String.valueOf(time==null));


        if (mLastLocation != null) {
            latitude.setText(String.valueOf(mLastLocation.getLatitude()));
            longitude.setText(String.valueOf(mLastLocation.getLongitude()));
            time.setText(mLastUpdateTime);
        }
    }

}