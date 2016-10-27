package aics.uit.khoaluan.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hlam393 on 20/03/2015.
 * item product on app
 */
public class ItemProduct implements Serializable {
    private String strID;
    private String postTitle; //name product
    private String postCategory; // Danh muc
    private String postDescribe; //mo ta
    private String postMasterName; //ten nguoi ban
    private String postPhoneNumber; //sdt nguoi ban
    private String postEmail; //email nguoi ban
    private String postAddress; //dia chi (text) nguoi ban
    private String postLatitude; //
    private String postLongtitude; //
    private String postPrice; //gia ca
    private String postSameProduct; //thong tin chi tiet
    private String postTime;
    private ArrayList<String> postArrayImage = new ArrayList<>();
    private String distance;
    private String imgPresent;

    public String getImgPresent() {
        return imgPresent;
    }

    public void setImgPresent(String imgPresent) {
        this.imgPresent = imgPresent;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStrID() {
        return strID;
    }

    public void setStrID(String strID) {
        this.strID = strID;
    }

    public ArrayList<String> getPostArrayImage() {
        return postArrayImage;
    }

    public void setPostArrayImage(ArrayList<String> postArrayImage) {
        this.postArrayImage = postArrayImage;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostDescribe() {
        return postDescribe;
    }

    public void setPostDescribe(String postDescribe) {
        this.postDescribe = postDescribe;
    }

    public String getPostMasterName() {
        return postMasterName;
    }

    public void setPostMasterName(String postMasterName) {
        this.postMasterName = postMasterName;
    }

    public String getPostPhoneNumber() {
        return postPhoneNumber;
    }

    public void setPostPhoneNumber(String postPhoneNumber) {
        this.postPhoneNumber = postPhoneNumber;
    }

    public String getPostEmail() {
        return postEmail;
    }

    public void setPostEmail(String postEmail) {
        this.postEmail = postEmail;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostLatitude() {
        return postLatitude;
    }

    public void setPostLatitude(String postLatitude) {
        this.postLatitude = postLatitude;
    }

    public String getPostLongtitude() {
        return postLongtitude;
    }

    public void setPostLongtitude(String postLongtitude) {
        this.postLongtitude = postLongtitude;
    }

    public String getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

    public String getPostSameProduct() {
        return postSameProduct;
    }

    public void setPostSameProduct(String postSameProduct) {
        this.postSameProduct = postSameProduct;
    }
}
