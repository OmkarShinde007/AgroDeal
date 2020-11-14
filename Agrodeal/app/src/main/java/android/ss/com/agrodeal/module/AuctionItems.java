package android.ss.com.agrodeal.module;

public class AuctionItems {

    private int auctionId;

    private int biddingId;

    private int dealerId;

    private String bidTime;

    private float bidAmount;

    private int farmerId;

    private String dealerName;

    private String farmerName;

    private String cropName;

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(int biddingId) {
        this.biddingId = biddingId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public AuctionItems(int auctionId, int biddingId, int dealerId, String bidTime, float bidAmount, int farmerId, String dealerName, String farmerName, String cropName) {
        this.auctionId = auctionId;
        this.biddingId = biddingId;
        this.dealerId = dealerId;
        this.bidTime = bidTime;
        this.bidAmount = bidAmount;
        this.farmerId = farmerId;
        this.dealerName = dealerName;
        this.farmerName = farmerName;
        this.cropName = cropName;
    }
}

