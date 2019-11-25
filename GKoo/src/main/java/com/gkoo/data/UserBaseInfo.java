package com.gkoo.data;

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
    
        
        public UserBaseInfo setUserid(String userid) {
            this.userid = userid;
            return this;
        }
        
        public UserBaseInfo withFirstName(String userid) {
            this.userid = userid;
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
            this.email = nameEng;
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

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getUserid() {
            return userid;
        }

        public String getNameEng() {
            return nameEng;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public String getTransitNr() {
            return transitNr;
        }

        public String getZipCode() {
            return zipCode;
        }

        public String getPhonePrefic() {
            return phonePrefic;
        }

        public String getPhoneInterfix() {
            return phoneInterfix;
        }

        public String getPhoneSuffix() {
            return phoneSuffix;
        }
}