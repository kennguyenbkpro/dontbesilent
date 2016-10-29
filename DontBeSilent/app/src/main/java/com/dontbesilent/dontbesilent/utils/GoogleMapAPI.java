package com.dontbesilent.dontbesilent.utils;

import com.dontbesilent.dontbesilent.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by lamhx on 29/10/2016.
 */
public class GoogleMapAPI {
    public static void addMarkers(GoogleMap googleMap, LatLng location, String title, String snippet) {
        if (googleMap != null) {

            // a draggable marker with title and snippet
            googleMap.addMarker(new MarkerOptions().position(location)
                    .title(title).snippet(snippet)
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mylocation)));

            // marker with custom color
//            googleMap.addMarker(new MarkerOptions()
//                    .position(BROOKLYN_BRIDGE)
//                    .title("First Pit Stop")
//                    .icon(BitmapDescriptorFactory
//                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//            // marker with opacity
//            googleMap.addMarker(new MarkerOptions().position(LOWER_MANHATTAN)
//                    .title("Second Pit Stop").snippet("Best Time: 6 Secs")
//                    .alpha(0.4f));
//
//            // marker using custom image
//            googleMap.addMarker(new MarkerOptions()
//                    .position(WALL_STREET)
//                    .title("Wrong Turn!")
//                    .icon(BitmapDescriptorFactory
//                            .fromResource(R.drawable.ic_mylocation)));

//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                    BROOKLYN_BRIDGE, 13));

        }
    }
}
