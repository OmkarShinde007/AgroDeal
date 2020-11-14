package android.ss.com.agrodeal.retro;

import android.content.Context;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class Retro {

    public static String BASE_URL = "http://localhost:8080/Agrodeal/rest/AppService/";

    public static RestAdapter getClient(Context context) {

        Shareprefrance shareprefrance = new Shareprefrance();
        BASE_URL = "http://" + shareprefrance.getServerURL(context) + "/Agrodeal/rest/AppService/";

        Log.e("Retro", " BASE URL AS " + BASE_URL);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return adapter;
    }

    public static Retrointerface getInterface(Context context) {
        return getClient(context).create(Retrointerface.class);
    }

    public interface Retrointerface {

        @FormUrlEncoded
        @POST("/Login")
        public void loginUser(
                @Field("email") String email,
                @Field("password") String password,

                Callback<LoginResponse> response);

        @FormUrlEncoded
        @POST("/dealerLogin")
        public void dealerLogin(
                @Field("email") String email,
                @Field("password") String password,

                Callback<LoginResponse> response);

        @FormUrlEncoded
        @POST("/brokerLogin")
        public void brokerLogin(
                @Field("email") String email,
                @Field("password") String password,

                Callback<LoginResponse> response);

        @FormUrlEncoded
        @POST("/Registration")
        public void registerUser(
                @Field("email") String email,
                @Field("password") String password,
                @Field("name") String name,
                @Field("mobile") String mobile,
                @Field("city") String city,

                Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/brokerRegistration")
        public void registerBroker(
                @Field("email") String email,
                @Field("password") String password,
                @Field("name") String name,
                @Field("mobile") String mobile,
                @Field("city") String city,
                @Field("referralCode") String referralCode,

                Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/dealerRegistration")
        public void registerDealer(
                @Field("email") String email,
                @Field("password") String password,
                @Field("name") String name,
                @Field("mobile") String mobile,
                @Field("city") String city,
                @Field("referralCode") String referralCode,

                Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/addCrop")
        public void addCrop(
                @Field("cropName") String email,
                @Field("cropRate") String password,
                @Field("cropQuality") String quality,
                @Field("cropDetail") String cropDetail,
                @Field("farmerId") String farmerId,
                Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/myCrops")
        public void myCrops(@Field("farmerId") String farmerId, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/allAuctions")
        public void allAuctions(@Field("test") String test, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/allFarmers")
        public void allFarmers(@Field("test") String test, Callback<GenralResponse> response);


        @FormUrlEncoded
        @POST("/doBidding")
        public void doBidding(@Field("dealerId") String user, @Field("auctionId") int auctionId, @Field("bidAmount") String bidAmount, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/getBidding")
        public void getBidding(@Field("auctionId") int auctionId, Callback<AuctionResponse> response);

        @FormUrlEncoded
        @POST("/wonCrop")
        public void wonCrop(@Field("auctionId") int auctionId, Callback<AuctionResponse> response);

        @FormUrlEncoded
        @POST("/allDealers")
        public void allDealers(@Field("test") String test, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/addReview")
        public void addReview(@Field("farmerId") String farmerId, @Field("dealerId") int dealerId,
                              @Field("review") String review, @Field("entityName") String entityName,
                              @Field("rating") String rating, Callback<GenralResponse> response);



        @FormUrlEncoded
        @POST("/brokerBiddings")
        public void brokerBiddings(@Field("brokerId") int brokerId, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/viewDealer")
        public void viewDealer(@Field("dealerId") int dealerId, Callback<DealerResponse> response);

        @FormUrlEncoded
        @POST("/viewFarmer")
        public void viewFarmer(@Field("farmerId") int farmerId, Callback<FarmerResponse> response);

        @FormUrlEncoded
        @POST("/viewBroker")
        public void viewBroker(@Field("brokerId") int brokerId, Callback<BrokerResponse> response);


        @FormUrlEncoded
        @POST("/soldCrops")
        public void soldCrops(@Field("farmerId") int farmerId, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/wonnedCrops")
        public void wonnedCrops(@Field("dealerId") int dealerId, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/validate")
        public void validate(@Field("userId") String userId,@Field("userType") String userType,@Field("otp") String otp, Callback<GenralResponse> response);

        @FormUrlEncoded
        @POST("/checkValid")
        public void checkValid(@Field("userId") String userId,@Field("userType") String userType, Callback<GenralResponse> response);

    }
}
