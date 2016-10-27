package aics.uit.khoaluan;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import aics.uit.khoaluan.item.ItemProduct;

/**
 * Created by hlam393 on 21/03/2015.
 */
public class CacheVariant {

    //file name product favorite
    public static String STR_FILE_PRODUCT_FAVOURITE = "product_favourite";
    //file name product save
    public static String STR_FILE_PRODUCT_SAVE = "product_save";
//    Item Info product detail

    //Current Location user
    public static double D_LAT = 0.0;
    public static double D_LONG = 0.0;

    public static Location LOCATION_CURRENT = null;

    public static LatLng LATLNG_END = null;

    public static ArrayList<ItemProduct> ITEM_PRODUCT_FAVOURITE;

    public static  ArrayList<ItemProduct> ITEM_PRODUCT_SAVE;

    public static ArrayList<ItemProduct> ARRAY_ITEM_PRODUCT;

    public static ItemProduct ITEM_INFO_PRODUCT_DETAIL = null;
}
