package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startOnlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_online);

        Button rec = (Button) findViewById(R.id.btn_record);
        rec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(startOnlineActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

        /*Button graphics = (Button) findViewById(R.id.btn_grafik);
        graphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(startOnlineActivity.this, GrapicsActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
