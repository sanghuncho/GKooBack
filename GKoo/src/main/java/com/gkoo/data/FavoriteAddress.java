package com.gkoo.data;

/**
 *
 * @author sanghuncho
 *
 * @since  04.12.2019
 *
 */
public class FavoriteAddress {
    private int id;
    private String userid;    
    private String nameKor;
    private String nameEng;
    private String transitNr;
    
    private String phonePrefic;
    private String phoneInterfix;
    private String phoneSuffix;
    private String phoneSecond;
    
    private String zipCode;
    private String address;
    private String addressDetails;
    
    public FavoriteAddress() {}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getTransitNr() {
        return transitNr;
    }

    public void setTransitNr(String transitNr) {
        this.transitNr = transitNr;
    }

    public String getPhonePrefic() {
        return phonePrefic;
    }

    public void setPhonePrefic(String phonePrefic) {
        this.phonePrefic = phonePrefic;
    }

    public String getPhoneInterfix() {
        return phoneInterfix;
    }

    public void setPhoneInterfix(String phoneInterfix) {
        this.phoneInterfix = phoneInterfix;
    }

    public String getPhoneSuffix() {
        return phoneSuffix;
    }

    public void setPhoneSuffix(String phoneSuffix) {
        this.phoneSuffix = phoneSuffix;
    }

    public String getPhoneSecond() {
        return phoneSecond;
    }

    public void setPhoneSecond(String phoneSecond) {
        this.phoneSecond = phoneSecond;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}