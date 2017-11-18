package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class startOfflineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_offline);


        Button btnOff = (Button) findViewById(R.id.buttonOff);
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View arg0) {
                Intent intent = new Intent(startOfflineActivity.this, menuOfflineActivity.class);
                startActivity(intent);
            }
        });


    }
}
