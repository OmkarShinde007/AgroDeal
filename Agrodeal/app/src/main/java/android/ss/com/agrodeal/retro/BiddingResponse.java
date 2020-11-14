package android.ss.com.agrodeal.retro;

public class BiddingResponse {

    private int biddingId;

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


    private int dealerId;

    private String bidTime;

    private float bidAmount;

    private String dealerName;



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
}
