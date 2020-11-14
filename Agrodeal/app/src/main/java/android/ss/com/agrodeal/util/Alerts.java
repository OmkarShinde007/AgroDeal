package android.ss.com.agrodeal.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alerts {

    public static void showAlert(String msg, Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
