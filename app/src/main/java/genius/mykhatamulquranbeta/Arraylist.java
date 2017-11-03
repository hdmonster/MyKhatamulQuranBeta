package genius.mykhatamulquranbeta;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DZAKY_PC on 11/2/2017.
 */

public class Arraylist extends Activity {
    private List<List<String>> halaman = new ArrayList<List<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        ArrayList<String>  hal= new ArrayList<String>();
        hal.add("0");
        hal.add("1");
        hal.add("2");
        hal.add("3");
        hal.add("4");
        hal.add("5");
        hal.add("6");
        hal.add("7");
        hal.add("8");
        hal.add("9");
        hal.add("10");


        Object[] mStringArray = hal.toArray();

        for(int i = 0; i < mStringArray.length ; i++){
            Log.d("halaman",(String)mStringArray[i]);
        }
    }

}
