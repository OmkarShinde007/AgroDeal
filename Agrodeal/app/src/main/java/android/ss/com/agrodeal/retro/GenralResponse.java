package android.ss.com.agrodeal.retro;

public class GenralResponse {


    private String result;

    private String auctionResult;

    private CropsResponse jarray[];

    private AuctionResponse auctionJarray[];

    private DealerResponse dealerJarray[];

    private FarmerResponse farmerJarray[];

    private SoldResponse soldJarray[];

    public DealerResponse[] getDealerJarray() {
        return dealerJarray;
    }

    public String getAuctionResult() {
        return auctionResult;
    }

    public String getResult() {
        return result;
    }

    public CropsResponse[] getJarray() {
        return jarray;
    }

    public AuctionResponse[] getAuctionJarray() {
        return auctionJarray;
    }

    public FarmerResponse[] getFarmerJarray() {
        return farmerJarray;
    }

    public SoldResponse[] getSoldJarray() {
        return soldJarray;
    }
}
