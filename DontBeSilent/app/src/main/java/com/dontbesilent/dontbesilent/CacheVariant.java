package com.dontbesilent.dontbesilent;

import android.location.Location;

import com.dontbesilent.dontbesilent.item.ItemCampaign;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by lamhx on 29/10/2016.
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

    public static ArrayList<ItemCampaign> ITEM_CAMPAIGN_FAVOURITE;

    public static  ArrayList<ItemCampaign> ITEM_CAMPAIGN_SAVE;

    public static ArrayList<ItemCampaign> ARRAY_ITEM_CAMPAIGN;

    public static ItemCampaign ITEM_INFO_CAMPAIGN_DETAIL = null;
}
