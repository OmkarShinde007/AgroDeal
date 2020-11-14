package android.ss.com.agrodeal.retro;

public class SoldResponse {


   private int winnerId;

   private int cropId ;

   private String name;

   private String cropName;

    private double amount;

    private String soldTime;

    private String farmerName;

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(String soldTime) {
        this.soldTime = soldTime;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public SoldResponse(String name, String cropName, double amount, String soldTime,String farmerName) {
        this.name = name;
        this.cropName = cropName;
        this.amount = amount;
        this.soldTime = soldTime;
        this.farmerName = farmerName;
    }
}
