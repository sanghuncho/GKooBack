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
    
    private String phonenumberFirst;
    private String phonenumberSecond;
    
    private String zipCode;
    private String address;
    
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

    public String getPhonenumberFirst() {
        return phonenumberFirst;
    }

    public void setPhonenumberFirst(String phonenumberFirst) {
        this.phonenumberFirst = phonenumberFirst;
    }

    public String getPhonenumberSecond() {
        return phonenumberSecond;
    }

    public void setPhonenumberSecond(String phonenumberSecond) {
        this.phonenumberSecond = phonenumberSecond;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}