package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Loading_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
