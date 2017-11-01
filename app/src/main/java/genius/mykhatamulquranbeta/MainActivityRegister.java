package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private static final String URL = "http://ubkmart.com/MyKhatamulPhp/reg.php";

    public static final String NAME = "name";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pass";
    public static final String EMAIL = "email";




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
        final EditText Username =(EditText) findViewById(R.id.userku);
        final EditText Password =(EditText) findViewById(R.id.passwordku);
        //final EditText Confirm = (EditText) findViewById(R.id.confirku);
        final EditText Email =(EditText) findViewById(R.id.emailku);
        final EditText User =(EditText) findViewById(R.id.namaku);

        final String user = User.getText().toString().trim();
        final String username = Username.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String email = Email.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            System.out.println(response);
                            Toast.makeText(MainActivityRegister.this, "Account registered, please login", Toast.LENGTH_LONG).show();


                        }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivityRegister.this,"Network error",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user", username);
                params.put("name", user);
                params.put("email", email);
                params.put("pass", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
