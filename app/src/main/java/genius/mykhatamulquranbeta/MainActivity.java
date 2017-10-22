package genius.mykhatamulquranbeta;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    ViewPager viewPager;
    CustomSwipeAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.view_page);
        adpter = new CustomSwipeAdpter(this);
        viewPager.setAdapter(adpter);
    }
}