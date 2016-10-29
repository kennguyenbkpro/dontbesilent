package com.dontbesilent.dontbesilent.data;

/**
 * Created by CuTi on 10/29/2016.
 */

public class Donation {
    public static final int TYPE_MONEY = 0;
    public static final int TYPE_BOOK = 1;
    public static final int TYPE_CLOTHES = 2;
    public static final int TYPE_MEDICINE = 3;
    public static final int TYPE_OTHER = 4;

    public int type;
    public String donator;
    public CharSequence description;

    public int getTypeResource() {
        int resource = 0;
        switch (type) {
            case TYPE_MONEY:
                break;
            case TYPE_BOOK:
                break;
            case TYPE_CLOTHES:
                break;
            case TYPE_MEDICINE:
                break;
            default:
                break;
        }
        return resource;
    }
}
