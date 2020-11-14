package android.ss.com.agrodeal.util;

import java.util.Date;

public class Auction {

    private int auctionId;

    private int cropId;

    private String creationTime;

    private String cropName;

    private String cropQuality;

    private String cropDetail;

    private float cropRate;

    private int farmerId;

    private String farmerName;

    private String auctionStatus;

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropQuality() {
        return cropQuality;
    }

    public void setCropQuality(String cropQuality) {
        this.cropQuality = cropQuality;
    }

    public String getCropDetail() {
        return cropDetail;
    }

    public void setCropDetail(String cropDetail) {
        this.cropDetail = cropDetail;
    }

    public float getCropRate() {
        return cropRate;
    }

    public void setCropRate(float cropRate) {
        this.cropRate = cropRate;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public Auction(int auctionId, int cropId, String creationTime, String cropName, String cropQuality, String cropDetail, float cropRate, int farmerId, String farmerName, String auctionStatus) {
        this.auctionId = auctionId;
        this.cropId = cropId;
        this.creationTime = creationTime;
        this.cropName = cropName;
        this.cropQuality = cropQuality;
        this.cropDetail = cropDetail;
        this.cropRate = cropRate;
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.auctionStatus = auctionStatus;
    }
}
