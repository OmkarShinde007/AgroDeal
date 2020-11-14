package android.ss.com.agrodeal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.ss.com.agrodeal.activity.BrokerHomeActivity;
import android.ss.com.agrodeal.activity.DealerHomeActivity;
import android.ss.com.agrodeal.activity.HomeActivity;
import android.ss.com.agrodeal.activity.LoginActivity;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Context context = MainActivity.this;
    Spinner spinner;
    Locale myLocale;
    String currentLanguage = "en", currentLang;

    void checkLogin()
    {
        Resources res = getResources();
        final boolean isLogin = getSharedPreferences("key_pref", MODE_PRIVATE).getBoolean("key_islogin", false);
        if (isLogin&&Shareprefrance.getUserType(context).equals("farmer")) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
        if (isLogin&&Shareprefrance.getUserType(context).equals("dealer")) {
            startActivity(new Intent(getApplicationContext(), DealerHomeActivity.class));
        }
        if (isLogin&&Shareprefrance.getUserType(context).equals("broker")) {
            startActivity(new Intent(getApplicationContext(), BrokerHomeActivity.class));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogin();

        currentLanguage = getIntent().getStringExtra(currentLang);

        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();

        list.add("Select language");
        list.add("English");
        list.add("Hindi");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hi");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(new Intent(context, LoginActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

}
