package com.dontbesilent.dontbesilent.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dontbesilent.dontbesilent.CacheVariant;
import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.item.ItemCampaign;
import com.dontbesilent.dontbesilent.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;


public class MapsFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private View v = null;
    private HashMap<Marker, ItemCampaign> eventMarkerMap;
    private Location location;
    private CameraPosition cameraPosition;

    public static MapsFragment getInstance() {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    public static MapsFragment newInstance(FragmentManager fm) {
        MapsFragment mapsActivity = new MapsFragment();
        fragmentManager = fm;
        return mapsActivity;
    }

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private LatLng mLatLngStart, mLatLngEnd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_maps);
//        setUpMapIfNeeded();
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(lat).zoom(12).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_maps, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded(false);
        /*if (CacheVariant.LATLNG_END != null)
            directions(CacheVariant.LATLNG_END);*/
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded(final boolean isDirection) {
        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment. getSupportFragmentManager()
            ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    if (CacheVariant.LATLNG_END != null)
                        directions(CacheVariant.LATLNG_END);

                    mMap.setMyLocationEnabled(true);
                    updateLocation(isDirection);
                    // Check if we were successful in obtaining the map.
                    if (mMap != null) {
                        setUpMap();
                    }

                    mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
                }
            });

        /*} else {
            if (CacheVariant.LATLNG_END != null)
                directions(CacheVariant.LATLNG_END);

            mMap.setMyLocationEnabled(true);
            updateLocation(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }*/

    }

    public Marker placeMarker(ItemCampaign eventInfo) {
        if (mMap != null) {
            BitmapDescriptor bmpIcon = null;
            {
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_maker_default);
            }

            Marker m = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(eventInfo.mLatitude), Double.parseDouble(eventInfo.mLongtitude)))
                            .title(eventInfo.mTitleDecription)
                            .snippet(eventInfo.mDescription)
                            .icon(bmpIcon)
            );
            return m;
        }
        return null;
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
//        updateLocation();

        eventMarkerMap = new HashMap<Marker, ItemCampaign>();

        //add market into map CacheVariant.ARRAY_ITEM_CAMPAIGN
        if (CacheVariant.ARRAY_ITEM_CAMPAIGN != null && CacheVariant.ARRAY_ITEM_CAMPAIGN.size() > 0) {
            for (int i = 0; i < CacheVariant.ARRAY_ITEM_CAMPAIGN.size(); i++) {
                ItemCampaign item = CacheVariant.ARRAY_ITEM_CAMPAIGN.get(i);
                eventMarkerMap.put(placeMarker(item), item);
            }
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                CacheVariant.ITEM_INFO_CAMPAIGN_DETAIL = eventMarkerMap.get(marker);
                Log.e("@LamHX", "Click Marker");
                //TODO
            }
        });
    }

    public void eventClickMarket() {
        if (mMap != null) {
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    // show dialog show info marker

                    Toast.makeText(getActivity(),
                            "Marker Clicked: " + marker.getTitle(), Toast.LENGTH_LONG)
                            .show();
                    return false;
                }
            });
        }
    }

    public void directions(LatLng latLngEnd) {
        setUpMapIfNeeded(true);
        mLatLngStart = new LatLng(CacheVariant.LOCATION_CURRENT.getLatitude(), CacheVariant.LOCATION_CURRENT.getLongitude());
        mLatLngEnd = latLngEnd;
        CameraUpdate center = CameraUpdateFactory.newLatLng(mLatLngStart);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

    }

    private void updateLocation(boolean isDirection) {
        GPSTracker objGPSTracker = new GPSTracker(getActivity());
        objGPSTracker.stopUsingGPS();
        CacheVariant.LOCATION_CURRENT = objGPSTracker.getLocation();
        if (CacheVariant.LOCATION_CURRENT != null) {
            LatLng objLatLng = new LatLng(objGPSTracker.getLatitude(), objGPSTracker.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(objLatLng, 20));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
        }
    }

}
