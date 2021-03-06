package genius.mykhatamulquranbeta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import genius.mykhatamulquranbeta.list.loginVar;
import genius.mykhatamulquranbeta.util.Session;

public class LoginActivity extends AppCompatActivity {

    private TextView reg,off;
    private EditText user,pass;
    Button login;
    Context context;
    private Session session;

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
                doubleBackToExitPressedOnce = false;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);

        context = LoginActivity.this;
        session = new Session(this);


        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogin);
        reg = (TextView) findViewById(R.id.txtReg) ;
        off = (TextView) findViewById(R.id.txtOff) ;

        TextView reg = (TextView) findViewById(R.id.txtReg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this, MainActivityRegister.class);
                startActivity(intent);
            }
        });

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,startOnlineActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = user.getText().toString();
                String pass1 = pass.getText().toString();

                if (user1.equals("") || pass1.equals("")) {
                    Toast.makeText(LoginActivity.this, "Fill the available form", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(context, user1 +" "+ pass1, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Please Wait...", Toast.LENGTH_LONG).show();
                    session.setLoggedin(true);
                    loggingIn(user1, pass1);
                    finishAffinity();
                    finish();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivityRegister.class);
                startActivity(intent);
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, startOfflineActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loggingIn( final String UserLogin,  final String PasswordLogin  ){

       // progressDialog.setMessage("Loading...");
//        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://ubkmart.com/MyKhatamulPhp/login.php?User="+UserLogin+"&Pass="+PasswordLogin+" ", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response){

                if(response.contains(loginVar.LOGIN_SUCCESS)){

                    go();
                }else {

                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

                Toast.makeText(LoginActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(loginVar.username, UserLogin);
                params.put(loginVar.password, PasswordLogin);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void go(){
        Intent intent = new Intent(this, startOnlineActivity.class);
        //session.createLoginSession(user.getText().toString(),pass.getText().toString());
        startActivity(intent);
        finish();
    }

}

