package com.example.yeasin.mapsviews;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void onSearch(View view){
        EditText location_tf = (EditText)findViewById(R.id.ts);
        String loction = location_tf.getText().toString();
        List<Address> addressList = null;
        if(loction == null || !loction.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(loction, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("khulna"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }

    }
    public void changeType(View view){
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        }else{
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }


    public void onlatLngSearch(View view){
        EditText latitude_tf  = (EditText)findViewById(R.id.lati);
        EditText longitude_tf  = (EditText)findViewById(R.id.lon);
        String la = latitude_tf.getText().toString();
        String lo = longitude_tf.getText().toString();
       // Double latitude = Double.parseDouble(latitude_tf.getText().toString());
       // Double longitude = Double.parseDouble(longitude_tf.getText().toString());
        if((la == null || !la.equals(""))&&(lo == null || !lo.equals(""))){
            Double latitude = Double.parseDouble(latitude_tf.getText().toString());
            Double longitude = Double.parseDouble(longitude_tf.getText().toString());
            LatLng latLng = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title("khulna"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }else{
            Toast.makeText(this,
                    "Please enter latitude/longitude values",
                    Toast.LENGTH_LONG).show();
            return;
            /*LatLng latLng = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title("khulna"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));*/
        }


    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.8167, 89.5500)).title("khulna"));

        mMap.setMyLocationEnabled(true);
    }
}
