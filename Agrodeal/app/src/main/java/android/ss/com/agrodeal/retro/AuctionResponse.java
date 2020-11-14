package android.ss.com.agrodeal.retro;

import java.util.Date;

public class AuctionResponse {

    private int auctionId;

    private int cropId;

    private String creationTime;

    private String cropName;

    private String cropQuality;

    private String cropDetail;

    private float cropRate;

    private int farmerId;

    private String farmerName;

    private String result;

    private int biddingId;

    private int dealerId;

    private String bidTime;

    private float bidAmount;

    private String dealerName;

    public String auctionStatus;

    public float getBidAmount() {
        return bidAmount;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getBidTime() {
        return bidTime;
    }

    public int getDealerId() {
        return dealerId;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public int getAuctionId() {
        return auctionId;
    }


    public int getCropId() {
        return cropId;
    }


    public String getCreationTime() {
        return creationTime;
    }


    public String getCropName() {
        return cropName;
    }


    public String getCropQuality() {
        return cropQuality;
    }


    public String getCropDetail() {
        return cropDetail;
    }


    public float getCropRate() {
        return cropRate;
    }


    public int getFarmerId() {
        return farmerId;
    }


    public String getFarmerName() {
        return farmerName;
    }

    public String getResult() {
        return result;
    }


    public String getAuctionStatus() {
        return auctionStatus;
    }

    public BiddingResponse jarray[];

    public BiddingResponse[] getJarray() {
        return jarray;
    }
}
