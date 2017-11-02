package genius.mykhatamulquranbeta;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import genius.mykhatamulquranbeta.helper.SessionManager;
import genius.mykhatamulquranbeta.list.loginVar;

public class LoginActivity extends AppCompatActivity {

    private EditText user,pass;
    private ProgressDialog progressDialog;
    private Context context;
    SessionManager session;
    Button login;

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



        progressDialog = new ProgressDialog(context);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = user.getText().toString();
                String pass1 = pass.getText().toString();

                if (user1.equals("") || pass1.equals("")) {
                    Toast.makeText(context, "Fill the available form", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, user1 +" "+ pass1, Toast.LENGTH_SHORT).show();
                    //loggingIn(user1, pass1);
                }
            }
        });
    }

    private void loggingIn( final String UserLogin,  final String PasswordLogin  ){

        progressDialog.setMessage("Loading...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginVar.LOGIN_URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response){

                if(response.contains(loginVar.LOGIN_SUCCESS)){
                    hideDialog();
                    go();
                }else {
                    hideDialog();
                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                hideDialog();
                Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
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
        session.createLoginSession(user.getText().toString(),pass.getText().toString());
        startActivity(intent);
        finish();
    }

    private void showDialog(){
        if(!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog(){
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

        /*TextView reg = (TextView) findViewById(R.id.txtReg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this, MainActivityRegister.class);
                startActivity(intent);
            }
        });
    }*/
}
