package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Loading_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading__screen);
    Thread myThread = new Thread(){
       @Override
       public void run(){
           try {
               sleep(3000);
               Intent intent = new Intent(getApplicationContext(),Keterangan.class);
               startActivity(intent);
               finish();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    };
    myThread.start();
    }
}
