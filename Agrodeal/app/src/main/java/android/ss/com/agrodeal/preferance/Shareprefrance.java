package android.ss.com.agrodeal.preferance;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Shareprefrance {

    private static final String KEY_PREF = "key_pref";
    private static final String KEY_PREFERANCE = "key_prefereance";

    private static final String KEY_ID = "key_id";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_EMAIl = "key_email";
    private static final String KEY_PASSWORD = "key_password";
    private static final String KEY_PHONE = "key_phone";
    private static final String KEY_LOCATION_ID = "key_location_id";
    private static final String KEY_USER_TYPE = "key_user_type";
    private static final String KEY_ISLOGIN = "key_islogin";
    private static final String KEY_SERVER_URL = "key_server_url";
    private static final String KEY_ISQUESTION_SET = "key_is_Question_set";
    private static final String KEY_FCM_KEY = "key_fcm_key";


    public static void loginUser(Context context, String name, String user_id,String mobile ,String email,boolean isLogin, String userType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ID, user_id);
        editor.putString(KEY_PHONE, mobile);
        editor.putString(KEY_EMAIl, email);
        editor.putBoolean(KEY_ISLOGIN, isLogin);
        editor.putString(KEY_USER_TYPE,userType);
        editor.apply();
    }

    public void setServerURL(Context context, String url) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERANCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SERVER_URL, url);
        editor.apply();
    }

    public String getServerURL(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERANCE, Context.MODE_PRIVATE);
        Log.e("####", " Preferance IP as " + sharedPreferences.getString(KEY_SERVER_URL, ""));
        return sharedPreferences.getString(KEY_SERVER_URL, "192.168.1.208:8080");
    }

    public void setFCMKey(Context context, String fcm) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERANCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FCM_KEY, fcm);
        editor.apply();
    }

    public String getFCMkey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERANCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FCM_KEY, "");
    }


    public void setQuestionSet(Context context, boolean isQuestionsste) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISQUESTION_SET, isQuestionsste);
        editor.apply();
    }

    public boolean isQuestionset(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_ISQUESTION_SET, false);
    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public boolean isLOgin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_ISLOGIN, false);
    }

    public static String getName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public int getLocationId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_LOCATION_ID, 0);
    }

    public String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIl, "");
    }

    public String getPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    public String getPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHONE, "");
    }

    public static String getUserType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TYPE, "");
    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, "");
    }




}
