package shippingService;

public class DeliveryDataObject {

    private String shopUrl;
    private String trackingTitle;
    private String trackingNumber;
    
    public DeliveryDataObject() {}

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getTrackingTitle() {
        return trackingTitle;
    }

    public void setTrackingTitle(String trackingTitle) {
        this.trackingTitle = trackingTitle;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
