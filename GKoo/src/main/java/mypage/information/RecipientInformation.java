package mypage.information;

public class RecipientInformation {
	private String nameKor;
	private String nameEng;
	private String transitNr;
	
	private String phonePrefic;
	private String phoneInterfix;
	private String phoneSuffix;
	private String phoneNr;
	
	private String adress;
	private String adressDetails;
	private String zipCode;
	private String fullAdress;
	private String usercomment;
	
	public RecipientInformation() {}

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
		this.phoneNr = phonePrefic + " " + phoneInterfix + " " + phoneSuffix;
	}

	public String getTransitNr() {
		return transitNr;
	}

	public void setTransitNr(String transitNr) {
		this.transitNr = transitNr;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAdressDetails() {
		return adressDetails;
	}

	public void setAdressDetails(String adressDetails) {
		this.adressDetails = adressDetails;
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
		this.fullAdress = this.adress+" "+this.adressDetails+", "+this.zipCode;
	}


	
}
