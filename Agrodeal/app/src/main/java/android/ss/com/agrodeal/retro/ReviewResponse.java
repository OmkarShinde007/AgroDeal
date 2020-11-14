package android.ss.com.agrodeal.retro;

public class ReviewResponse {

    private int reviewId;
    private String ratings;
    private String reviews;
    private String reviewTime;
    private String farmerName;
    private String dealerName;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public ReviewResponse(int reviewId, String ratings, String reviews, String reviewTime, String farmerName, String dealerName) {
        this.reviewId = reviewId;
        this.ratings = ratings;
        this.reviews = reviews;
        this.reviewTime = reviewTime;
        this.farmerName = farmerName;
        this.dealerName = dealerName;
    }
}
