package com.gkoo.data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("recipientData")
@Scope(value="prototype")
public class RecipientData {

    //DB nomalization userid, ordernumber should deleted.
//	private String userid;
//	private String orderNumber;
	
    private String nameKor;
	private String nameEng;
	private String transitNr;
	
	private String phonenumberFirst;
    private String phonenumberSecond;
    
	private String zipCode;
	private String address;
	private String usercomment;
	
	public RecipientData() {}
	 
//	public String getOrderNumber() {
//	     return orderNumber;
//	}
//
//	public void setOrderNumber(String orderNumber) {
//	    this.orderNumber = orderNumber;
//	}
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

//	public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }

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

    public static class Builder {
//	    private String userid;
//	    private String orderNumber;
	    
	    private String nameKor;
	    private String nameEng;
	    
	    private String transitNr;
	    
	    private String phonenumberFirst;
	    private String phonenumberSecond;
	    
	    private String zipCode;
	    private String address;
	    
	    private String usercomment;
	    
	    public Builder(){}
	    
//	    public Builder setUserid(String userid) {
//	        this.userid = userid;
//	        return this;
//	    }
//	    
//	    public Builder setOrderNumber(String orderNumber) {
//            this.orderNumber = orderNumber;
//            return this;
//        }
	    
	    public Builder setNameKorEng(String nameKor, String nameEng) {
            this.nameKor = nameKor;
            this.nameEng = nameEng;
            return this;
        }
	    
	    public Builder setTransitNr(String transitNr) {
            this.transitNr = transitNr;
            return this;
        }
	    
	    public Builder setPhoneData(String phonenumberFirst, String phonenumberSecond) {
            this.phonenumberFirst = phonenumberFirst;
            this.phonenumberSecond = phonenumberSecond;
            return this;
        }
	    
	    public Builder setAddressData(String zipCode, 
                String address) {
            this.zipCode = zipCode;
            this.address = address;
            return this;
        }
	    
	    public Builder setUsercomment(String usercomment) {
            this.usercomment = usercomment;
            return this;
        }
	    
	    public RecipientData build() {
	        RecipientData data = new RecipientData();
//	        data.setUserid(this.userid);
//	        data.orderNumber = this.orderNumber;
	        data.nameKor = this.nameKor;
	        data.nameEng = this.nameEng;
	        data.transitNr = this.transitNr;
	        data.phonenumberFirst = this.phonenumberFirst;
	        data.phonenumberSecond = this.phonenumberSecond;
	        data.zipCode = this.zipCode;
	        data.address = this.address;
	        data.usercomment = this.usercomment;
	        return data;
	    }
	}
}
