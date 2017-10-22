package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivityRegister extends AppCompatActivity {

    private static final String URL = "http://mysonschool.web.id/KhatamulQuran/Register.php";
    public static final String USERNAME = "user";
    public static final String GENDER = "gender";
    public static final String AGE = "umur";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Button reg = (Button) findViewById(R.id.button);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivityRegister.this, LoginActivity.class);
                startActivity(intent);
                SendData();
            }
        });

    }

    public void SendData()
    {
        final EditText Username =(EditText) findViewById(R.id.editText);
        final RadioButton Gender =(RadioButton) findViewById(R.id.radioButton);
        final EditText Age =(EditText) findViewById(R.id.editText2);
        final EditText Password =(EditText) findViewById(R.id.editText3);
        final EditText Email =(EditText) findViewById(R.id.editText4);
        final String username = Username.getText().toString().trim();
        final String gender = Gender.getText().toString().trim();
        final String age = Age.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String email = Email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(MainActivityRegister.this,"The Account Has been Created",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivityRegister.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",username);
                params.put("gender", gender);
                params.put("umur", age);
                params.put("email", email);
                params.put("pass", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finishAffinity();
                finish();
                System.exit(0);
            }
        }, 2000);
    }


}
