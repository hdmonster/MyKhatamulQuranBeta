package genius.mykhatamulquranbeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KeteranganActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterangan);

        Button mulai = (Button) findViewById(R.id.button3);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(KeteranganActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}