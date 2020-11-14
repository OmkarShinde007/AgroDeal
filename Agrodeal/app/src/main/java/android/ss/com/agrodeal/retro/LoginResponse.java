package android.ss.com.agrodeal.retro;


public class LoginResponse {

    String result;
    String userId;
    String name;
    String email;
    String city;

    String mobile;

    String userType;

    String status;

    public String getUserType() {
        return userType;
    }

    public String getMobile() {
        return mobile;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }
}
