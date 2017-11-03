package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import genius.mykhatamulquranbeta.util.Session;


public class startOnlineActivity extends AppCompatActivity {

    Button btnLogout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_online);

        session = new Session(this);

        if(!session.loggedin()){

            logout();

                }

                btnLogout = (Button) findViewById(R.id.btn_logout);

                btnLogout.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        logout();

                    }

                });





                Button graf = (Button) findViewById(R.id.btn_grafik);
                graf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View arg0) {
                        Intent intent = new Intent(startOnlineActivity.this, Grafik.class);
                        startActivity(intent);
                    }
                });


                Button start = (Button) findViewById(R.id.btn_start);
                start.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent(startOnlineActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });


            }
            public void openRec(View view) {
                Intent openRec = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.myfiles");
                startActivity(openRec);

            }



    private void logout(){

        session.setLoggedin(false);

        finish();

        startActivity(new Intent(startOnlineActivity.this,LoginActivity.class));

    }


}
