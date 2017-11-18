package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by DAFFA_PC on 18/11/2017.
 */

public class menuOfflineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_offlinemenu);

        Button startOff = (Button) findViewById(R.id.startofln);
        startOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(menuOfflineActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button continueOff = (Button) findViewById(R.id.continueofln);
        continueOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(menuOfflineActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        Button diagramOff = (Button) findViewById(R.id.diagramofln);
        diagramOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(menuOfflineActivity.this, Grafik.class);
                startActivity(intent);
            }
        });
    }

}
