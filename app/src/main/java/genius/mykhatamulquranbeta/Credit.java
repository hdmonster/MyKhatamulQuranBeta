package genius.mykhatamulquranbeta;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ALIF_PC on 11/1/2017.
 */

public abstract class Credit extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.credit);
    }
}