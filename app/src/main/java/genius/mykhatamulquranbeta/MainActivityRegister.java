package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Button reg = (Button) findViewById(R.id.button);
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent reg = new Intent(MainActivityRegister.this,LoginActivity.class);
                startActivity(reg);
            }
        });

    }
}
