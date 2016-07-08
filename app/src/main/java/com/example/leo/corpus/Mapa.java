package com.example.leo.corpus;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                    .addApi(LocationServices.API)
                    .build();
        }


    }
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
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
/*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
*/
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);


        LatLngBounds GRANADA = new LatLngBounds(
                new LatLng(37.205972, -3.614937), new LatLng(37.205972, -3.614937));
        mMap.moveCamera(newLatLngZoom(GRANADA.getCenter(), 17));


        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(37.208292, -3.614808))
                .add(new LatLng(37.206606, -3.618102))  // North of the previous point, but at the same longitude
                .add(new LatLng(37.203671, -3.616131))  // Same latitude, and 30km to the west
                .add(new LatLng(37.205508, -3.612859))  // Same longitude, and 16km to the south
                .add(new LatLng(37.208292, -3.614808));  // Same longitude, and 16km to the south

        // Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);


        LatLng caseta1 = new LatLng(37.207758, -3.615284);
        mMap.addMarker(new MarkerOptions()
                .position(caseta1)
                .title("AIRES DE FIESTA")
                .snippet("Caseta aires de fiesta")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aires_de_fiesta)));


        LatLng caseta2 = new LatLng(37.206919, -3.614607);
        mMap.addMarker(new MarkerOptions()
                .position(caseta2)
                .title("PEÑA LOS 17")
                .snippet("Caseta peña los 17")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.los_17)));



        LatLng caseta3 = new LatLng(37.206919, -3.615907);
        mMap.addMarker(new MarkerOptions()
                        .position(caseta3)
                        .title("LA ALBOREA")
                        .snippet("Caseta la alborea")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.la_alborea)));


        LatLng caseta4 = new LatLng(37.206080, -3.615307);
        mMap.addMarker(new MarkerOptions()
                .position(caseta4)
                .title("LA CACHUELA")
                .snippet("Caseta la cachuela")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.la_cachuela)));


        LatLng caseta5 = new LatLng(37.206530, -3.615617);
        mMap.addMarker(new MarkerOptions()
                .position(caseta5)
                .title("LA CASTAÑUELA")
                .snippet("Caseta la castañuela")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.la_castanuela)));

        LatLng caseta6 = new LatLng(37.206178, -3.613827);
        mMap.addMarker(new MarkerOptions()
                .position(caseta6)
                .title("LA REHUERTA")
                .snippet("Caseta la rehuerta")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.la_rehuerta)));



        LatLng granada = new LatLng(37.205972, -3.614937);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(granada));



    }

    @Override
    public void onConnected(Bundle bundle) {
        /*
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
        */
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}