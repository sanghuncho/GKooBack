package mypage.information;

public class RecipientData {
	private String memberId;
	private String orderNumber;
	
    private String nameKor;
	private String nameEng;
	private String transitNr;
	
	private String phonePrefic;
	private String phoneInterfix;
	private String phoneSuffix;
	private String phoneNr;
	
	private String zipCode;
	private String address;
	private String addressDetails;
	private String fullAdress;
	private String usercomment;
	
	public RecipientData() {}

	public String getMemberId() {
	     return memberId;
	}
	 
	public void setMemberId(String memberId) {
	   this.memberId = memberId;
	}
	 
	public String getOrderNumber() {
	     return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
	    this.orderNumber = orderNumber;
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

	public String getPhoneNr() {
		return phoneNr;
	}

	public void buildPhoneNr() {
		this.phoneNr = phonePrefic + "-" + phoneInterfix + "-" + phoneSuffix;
	}

	public String getTransitNr() {
		return transitNr;
	}

	public void setTransitNr(String transitNr) {
		this.transitNr = transitNr;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUsercomment() {
		return usercomment;
	}

	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}

	public String getFullAdress() {
		return fullAdress;
	}

	public void buildFullAdress() {
		this.fullAdress = this.address+" "+this.addressDetails+", "+this.zipCode;
	}
	
	public static class Builder {
	    private String memberId;
	    private String orderNumber;
	    
	    private String nameKor;
	    private String nameEng;
	    
	    private String transitNr;
	    
	    private String phonePrefic;
	    private String phoneInterfix;
	    private String phoneSuffix;
	    
	    private String zipCode;
	    private String address;
	    private String addressDetails;
	    
	    private String usercomment;
	    
	    public Builder(){}
	    
	    public Builder setMemberId(String memberId) {
	        this.memberId = memberId;
	        return this;
	    }
	    
	    public Builder setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }
	    
	    public Builder setNameKorEng(String nameKor, String nameEng) {
            this.nameKor = nameKor;
            this.nameEng = nameEng;
            return this;
        }
	    
	    public Builder setTransitNr(String transitNr) {
            this.transitNr = transitNr;
            return this;
        }
	    
	    public Builder setPhoneData(String phonePrefic, 
	            String phoneInterfix, String phoneSuffix) {
            this.phonePrefic = phonePrefic;
            this.phoneInterfix = phoneInterfix;
            this.phoneSuffix = phoneSuffix;
            return this;
        }
	    
	    public Builder setAddressData(String zipCode, 
                String address, String addressDetails) {
            this.zipCode = zipCode;
            this.address = address;
            this.addressDetails = addressDetails;
            return this;
        }
	    
	    public Builder setUsercomment(String usercomment) {
            this.usercomment = usercomment;
            return this;
        }
	    
	    public RecipientData build() {
	        RecipientData data = new RecipientData();
	        data.memberId = this.memberId;
	        data.orderNumber = this.orderNumber;
	        data.nameKor = this.nameKor;
	        data.nameEng = this.nameEng;
	        data.transitNr = this.transitNr;
	        data.phonePrefic = this.phonePrefic;
	        data.phoneInterfix =  this.phoneInterfix;
	        data.phoneSuffix = this.phoneSuffix;
	        data.zipCode = this.zipCode;
	        data.address = this.address;
	        data.addressDetails = this.addressDetails;
	        data.usercomment = this.usercomment;
	        return data;
	    }
	}
}
