package com.gkoo.data;

/**
 * @author sanghuncho
 *
 */
public class UserBaseInfo {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String userid;
    private String nameEng;
    private String detailAddress;
    private String transitNr;
    private String zipCode;
    private String phonePrefic;
    private String phoneInterfix;
    private String phoneSuffix;
    
    public UserBaseInfo() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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
        
    public UserBaseInfo setUserid(String userid) {
        this.userid = userid;
        return this;
    }    
    
    public UserBaseInfo withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
        
    public UserBaseInfo withLastName(String lastName) {
        this.lastName = lastName;
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
      
    public UserBaseInfo withNameEng(String nameEng) {
       this.nameEng = nameEng;
       return this;
    }
        
    public UserBaseInfo withDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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
        
    public UserBaseInfo withPhonePrefic(String phonePrefic) {
        this.phonePrefic = phonePrefic;
        return this;
    }
        
    public UserBaseInfo withPhoneInterfix(String phoneInterfix) {
        this.phoneInterfix = phoneInterfix;
        return this;
    }
        
    public UserBaseInfo withPhoneSuffix(String phoneSuffix) {
        this.phoneSuffix = phoneSuffix;
        return this;
    }
}