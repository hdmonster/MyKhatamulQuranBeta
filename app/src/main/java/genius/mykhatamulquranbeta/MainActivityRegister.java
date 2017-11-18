package genius.mykhatamulquranbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    public static final String PASSWORD = "pass";
    public static final String EMAIL = "email";
    public static final String CONFIRM = "confirm";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_register);


        final EditText Password =(EditText) findViewById(R.id.passwordku);
        final EditText Confirm = (EditText) findViewById(R.id.confirku);
        final EditText Email =(EditText) findViewById(R.id.emailku);
        final EditText User =(EditText) findViewById(R.id.namaku);


        Button reg = (Button) findViewById(R.id.button);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String user = User.getText().toString();
                String confirm = Confirm.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                Intent intent = new Intent(MainActivityRegister.this, LoginActivity.class);

                if(user.equals("")||password.equals("")||email.equals("")){
                    Toast.makeText(MainActivityRegister.this, "Please fill the available form", Toast.LENGTH_SHORT).show();
                }else if(!confirm.equals(password)){
                    Toast.makeText(MainActivityRegister.this, "Password not match", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(intent);
                    SendData();

                }
            }
        });
    }

    public void SendData(){
    final EditText Password =(EditText) findViewById(R.id.passwordku);
    final EditText Email =(EditText) findViewById(R.id.emailku);
    final EditText User =(EditText) findViewById(R.id.namaku);
    final EditText Confirm = (EditText) findViewById(R.id.confirku);

    final String user = User.getText().toString();
    final String password = Password.getText().toString();
    final String email = Email.getText().toString();
    final String confirm = Confirm.getText().toString();




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
                params.put(NAME, user);
                params.put(EMAIL, email);
                params.put(PASSWORD, password);
                params.put(CONFIRM, confirm);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
