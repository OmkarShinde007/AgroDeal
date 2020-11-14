package android.ss.com.agrodeal.retro;

public class FarmerResponse {

    private int farmerId;

    private String name;

    private String email;

    private String password;

    private String mobile;

    private String city;

    private String result;

    private double rating;

    private ReviewResponse jarray[];

    public ReviewResponse[] getJarray() {
        return jarray;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }

    public double getRating() {
        return rating;
    }

    public String getResult() {
        return result;
    }

    public FarmerResponse(int farmerId, String name, String mobile, String city,double rating) {
        this.farmerId = farmerId;
        this.name = name;
        this.mobile = mobile;
        this.city = city;
        this.rating = rating;
    }
}
