package com.gkoo.data;

/**
 * @author sanghuncho
 *
 */
public class UserBaseInfo {

    private String userid;
    private String nameKor;
    private String nameEng;
    private String email;
    private String transitNr;
    private String phonenumberFirst;
    private String phonenumberSecond;
    private String zipCode;
    private String address;
    
    public UserBaseInfo() {}

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
        
    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }
    
    public UserBaseInfo setUserid(String userid) {
        this.userid = userid;
        return this;
    }
        
    public UserBaseInfo withNameKor(String nameKor) {
        this.nameKor = nameKor;
        return this;
    }
    
    public UserBaseInfo withNameEng(String nameEng) {
        this.nameEng = nameEng;
        return this;
    }
    public UserBaseInfo withAddress(String address) {
        this.address = address;
        return this;
    }
        
    public UserBaseInfo withEmail(String email) {
        this.email = email;
        return this;
    }
        
    public UserBaseInfo withTransitNr(String transitNr) {
        this.transitNr = transitNr;
        return this;
    }
        
    public UserBaseInfo withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
    
    public UserBaseInfo withPhonenumberFirst(String phonenumberFirst) {
        this.phonenumberFirst = phonenumberFirst;
        return this;
    }
    
    public UserBaseInfo withPhonenumberSecond(String phonenumberSecond) {
        this.phonenumberSecond = phonenumberSecond;
        return this;
    }
}