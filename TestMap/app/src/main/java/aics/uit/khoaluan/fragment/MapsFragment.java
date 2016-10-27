package aics.uit.khoaluan.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import aics.uit.khoaluan.CacheVariant;
import aics.uit.khoaluan.R;
import aics.uit.khoaluan.item.ItemProduct;
import aics.uit.khoaluan.utils.GPSTracker;

public class MapsFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private View v = null;
    private HashMap<Marker, ItemProduct> eventMarkerMap;
    private Location location;
    private CameraPosition cameraPosition;

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
//        setUpMapIfNeeded();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded(false);
        if (CacheVariant.LATLNG_END != null)
            directions(CacheVariant.LATLNG_END);
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
    private void setUpMapIfNeeded(boolean isDirection) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment. getSupportFragmentManager()
            mMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
        updateLocation(isDirection);

    }
//    elif data["fTitleCategory"] == u'Xe':
//    condition = "WHERE (Category  LIKE '%" + u'Xe máy' + "%' OR Category  LIKE '%" + u'Ô tô' + "%' OR Category  LIKE '%" + u'Xe đạp' + "%' OR Category  LIKE '%" + u'Xe tải, Xe khác' + "%' OR Category  LIKE '%" + u'Phụ tùng, Phụ kiện xe' + "%') AND ( Title LIKE '%" + data["fTitle"] + "%'OR PhoneNumber LIKE '%" + data["fTitle"] + "%' OR Email LIKE '%" + data["fTitle"] + "%' OR ProductDescribe LIKE '%" + data["fTitle"] + "%' OR ProductDescribe LIKE '%" + data["fTitle"] + "%')"
//    elif data["fTitleCategory"] == u'Đồ điện tử':
//    condition = "WHERE (Category  LIKE '%" + u'Điện thoại di động' + "%' OR Category  LIKE '%" + u'Máy tính bảnh' + "%' OR Category  LIKE '%" + u'Máy tính, Laptop' + "%' OR Category  LIKE '%" + u'Máy ảnh, Máy quay' + "%' OR Category  LIKE '%" + u'Tivi, Loa, Amply, Máy nghe nhạc' + "%' OR Category  LIKE '%" + u'Phụ kiện, Link kiện' + "%') AND ( Title LIKE '%" + data["fTitle"] + "%'OR PhoneNumber LIKE '%" + data["fTitle"] + "%' OR Email LIKE '%" + data["fTitle"] + "%'OR ProductDescribe LIKE '%" + data["fTitle"] + "%' OR ProductDescribe LIKE '%" + data["f
    public Marker placeMarker(ItemProduct eventInfo) {
        if (mMap != null) {
            BitmapDescriptor bmpIcon = null;
            if(eventInfo.getPostCategory().equals("Xe máy")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_xemay.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_xemay);
            }else if(eventInfo.getPostCategory().equals("Ô tô")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_oto.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_oto);
            }else if(eventInfo.getPostCategory().equals("Xe đạp")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_xedap.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_xedap);
            } else if(eventInfo.getPostCategory().equals("Xe tải")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_xetai.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_xetai);
            }else if(eventInfo.getPostCategory().equals("Phụ tùng")||eventInfo.getPostCategory().equals("Phụ kiện xe")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_phutung.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_phutung);
            }else if(eventInfo.getPostCategory().equals("Điện thoại di động")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_dienthoai.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_dienthoai);
            }else if(eventInfo.getPostCategory().equals("Máy tính")||eventInfo.getPostCategory().equals("Máy tính bảng")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_maytinh.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_maytinh);
            }else if(eventInfo.getPostCategory().equals("Máy ảnh")){
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_mayanh.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_mayanh);
            }else{
//                bmpIcon = BitmapDescriptorFactory.fromAsset("ic_phukien.png");
                bmpIcon = BitmapDescriptorFactory.fromResource(R.raw.ic_phukien);
            }

            Marker m = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(eventInfo.getPostLatitude()), Double.parseDouble(eventInfo.getPostLongtitude())))
                            .title(eventInfo.getPostTitle())
                            .snippet(eventInfo.getPostDescribe())
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

        eventMarkerMap = new HashMap<Marker, ItemProduct>();

        //add market into map CacheVariant.ARRAY_ITEM_PRODUCT
        if (CacheVariant.ARRAY_ITEM_PRODUCT != null && CacheVariant.ARRAY_ITEM_PRODUCT.size() > 0) {
            for (int i = 0; i < CacheVariant.ARRAY_ITEM_PRODUCT.size(); i++) {
                ItemProduct item = CacheVariant.ARRAY_ITEM_PRODUCT.get(i);
                eventMarkerMap.put(placeMarker(item), item);
            }
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                CacheVariant.ITEM_INFO_PRODUCT_DETAIL = eventMarkerMap.get(marker);
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
//        if(!isDirection)
//            getDataMap(getActivity(), "", "", CacheVariant.LOCATION_CURRENT);
    }

    /*@Override
    public void onRoutingFailure() {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(PolylineOptions mPolyOptions, Route route) {

        PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(Color.BLUE);
        polyOptions.width(10);
        polyOptions.addAll(mPolyOptions.getPoints());
        mMap.addPolyline(polyOptions);

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(mLatLngStart);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mylocation));
        mMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(mLatLngEnd);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mylocation));
        mMap.addMarker(options);


        //reset location end
        CacheVariant.LATLNG_END = null;
    }
*/


}
