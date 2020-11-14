package android.ss.com.agrodeal.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Syneotek on 11/03/2017.
 */

public class SetFonts
{

    public static void setFontToActivity(Activity activity)
     {
        FontChangeCrawler fontChanger = new FontChangeCrawler(activity.getAssets(), "font/Aspire_DemiBold.ttf");
        fontChanger.replaceFonts((ViewGroup) activity.findViewById(android.R.id.content));
     }

    public static void setFontToAdapter(Context activity, View itemView)
     {
        FontChangeCrawler fontChanger = new FontChangeCrawler(activity.getAssets(), "font/Aspire_DemiBold.ttf");
        fontChanger.replaceFonts((ViewGroup) itemView);
     }

        public static void setFontToFragment(Context activity, ViewGroup view)
          {
            FontChangeCrawler fontChanger = new FontChangeCrawler(activity.getAssets(), "font/Aspire_DemiBold.ttf");
            fontChanger.replaceFonts((ViewGroup) view);
          }
}
