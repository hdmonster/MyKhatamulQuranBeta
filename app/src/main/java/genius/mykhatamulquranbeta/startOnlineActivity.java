package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class startOnlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_online);

        Button Graphp = (Button) findViewById(R.id.btn_grafik);
        Graphp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent Graphp= new Intent(startOnlineActivity.this,GrapichActivity.class);
                startActivity(Graphp);
            }
        });

    }
}
