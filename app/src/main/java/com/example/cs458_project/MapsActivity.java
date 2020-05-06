package com.example.cs458_project;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cs458_project.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.security.Provider;

import static android.location.LocationManager.GPS_PROVIDER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // GENERALVARIABLES

    // permissions
    private static final long LOCATION_REFRESH_TIME = 1;
    private static final float LOCATION_REFRESH_DISTANCE = 1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    // map variables
    public GoogleMap mMap;
    public LocationManager locationManager;

    // text variables
    private TextView tv;
    private EditText edit1,edit2;
    private Button b1;

    private String latText,longText;

    // latitude and longitude
    public int lng, lat;

    // the coordinates variable that keeps latitude and longitude
    private LatLng myLoc;

    // Log TAG format
    private static final String TAG = "Debug";

    // INITIATE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // create UI elements, prepare instance, activate UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        b1 = (Button)findViewById(R.id.button1);
        edit1 = (EditText)findViewById(R.id.editText1);
        edit2 = (EditText)findViewById(R.id.editText2);

        // the submit button that passes values of latitude and longitude to map view
        b1.setOnClickListener(new View.OnClickListener() {

            // click operation
            @Override
            public void onClick(View v) {

                // set latitude and longitude
                latText = edit1.getText().toString();
                lat = Integer.parseInt(latText);

                longText = edit2.getText().toString();
                lng= Integer.parseInt(longText);

                // set location
                myLoc = new LatLng(lng, lat);

                // debugger print
                Log.d("Debug","lat:"+lat+" "+"lng"+lng);

                // submit activity
                submit();
            }
        });
    }

    // the map view that shows the mark on the location retrieved
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // google api as map format
        mMap = googleMap;

        // Sample locations, inactive at the moment
        LatLng sydney = new LatLng(-34, 151);
        LatLng Turkey = new LatLng(40, 33);

        // put marker onto the map and set the location the center point of map
        mMap.addMarker(new MarkerOptions().position(myLoc).title("My Location!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
    }

    // render the map and send notification to the application for success case
    public void submit(){
        // Retrieve the content view that renders the map.
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }
}
