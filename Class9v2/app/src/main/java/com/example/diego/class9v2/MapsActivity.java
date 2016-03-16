package com.example.diego.class9v2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationServices request;
    private final int CODE = 0;


    //FUSED: location services

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        //Latitude / longitude / altitude

        LatLng classroom = new LatLng(20.737184, -103.454426);
        mMap.addMarker(new MarkerOptions().position(classroom).title("Classroom :v"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(classroom, 15));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);

        PolygonOptions zona1 = new PolygonOptions()
                .add(new LatLng(20.733939, -103.45430),
                        new LatLng(20.73387, -103.453622),
                        new LatLng(20.733295, -103.453693),
                        new LatLng(20.733350, -103.454393),
                        new LatLng(20.733939, -103.454331))
                .fillColor(Color.argb(90,255,0,0))
                .strokeColor(Color.argb(150,255, 0,0));
        // Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(zona1);
        polygon.setClickable(true);


        PolygonOptions zona2 = new PolygonOptions()
                .add(new LatLng(20.734727, -103.454262),
                        new LatLng(20.73472, -103.454189),
                        new LatLng(20.734664, -103.453489),
                        new LatLng(20.733912, -103.453620),
                        new LatLng(20.733970, -103.454329)
                )
                .fillColor(Color.argb(90,0,0,255))
                .strokeColor(Color.argb(0,0,0,255));
        Polygon polygon2 = mMap.addPolygon(zona2);
        polygon2.setClickable(true);

        PolygonOptions zona3 = new PolygonOptions()
                .add(new LatLng(20.732887, -103.455049),
                        new LatLng(20.732887, -103.455049),
                        new LatLng(20.732832, -103.454572),
                        new LatLng(20.731643, -103.454760),
                        new LatLng(20.731894, -103.456021),
                        new LatLng(20.732461, -103.455978),
                        new LatLng(20.732441, -103.455603)
                )
                .fillColor(Color.argb(90,0,0,255))
                .strokeColor(Color.argb(0, 0, 0, 255));
        Polygon polygon3 = mMap.addPolygon(zona3);
        polygon3.setClickable(true);

        PolygonOptions zona4 = new PolygonOptions()
                .add(new LatLng(20.732933, -103.457218),
                        new LatLng(20.732933,-103.457218),
                        new LatLng(20.732211, -103.456038),
                        new LatLng(20.731915, -103.456108),
                        new LatLng(20.732116, -103.456923),
                        new LatLng(20.732557, -103.457256)
                )
                .fillColor(Color.argb(90,0,0,255))
                .strokeColor(Color.argb(0, 255, 0, 0));

        ;
        Polygon polygon4 = mMap.addPolygon(zona4);
        polygon4.setClickable(true);

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            public void onPolygonClick(Polygon poly) {

                Log.e("click", poly.getId().toString());
                changeActivity(poly.getId().toString());

            }
        });
    }

    public void changeActivity(String s)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("Input",s);
        startActivityForResult(intent, CODE);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("MARKER", marker.toString());
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}
