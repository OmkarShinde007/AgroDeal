package android.ss.com.agrodeal.util;

public class Dealer {

    private int dealerId;

    private String name;

    private String email;


    private String mobile;

    private String city;


    private double rating;

    private String status;

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Dealer(int dealerId, String name, String email, String mobile, String city, double rating, String status) {
        this.dealerId = dealerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.rating = rating;
        this.status = status;
    }
}
