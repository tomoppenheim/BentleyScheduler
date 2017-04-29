package com.example.tuffy_josh.termproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapofcampus extends FragmentActivity implements OnMapReadyCallback {
    private static final float zoom = 16.37f;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       //
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //center map and set zoom level
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.3865, -71.2207), zoom));

        //set markers of colleges in boston
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.3881, -71.2209))
                .title("Jennison Hall")
                .snippet("Math Lab, Science labs, and Academic Advising")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.387400, -71.220476))
                .title("Smith Academic Technology")
                .snippet("CIS Sandbox, Trading Room")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.387281, -71.218521))
                .title("Adamian Academic Center")
                .snippet("Wilder pavilion")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.387281, -71.219637))
                .title("Lindsay Hall")
                .snippet("koumantzelis Auditorium")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));


        //set listeners
        mMap.setOnMarkerClickListener(//if user clicks marker then webview widget tab will home up to that colleges home page
                new GoogleMap.OnMarkerClickListener() {

                    public boolean onMarkerClick(Marker m) {
                        String title = m.getTitle();
                        String snip = m.getSnippet();
                        Toast.makeText(getApplicationContext(), title + "\n" + snip, Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
        );

    }
}
