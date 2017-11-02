package genius.mykhatamulquranbeta.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DZAKY_PC on 11/2/2017.
 */

public class Session {
    SharedPreferences prefs;

    SharedPreferences.Editor editor;

    Context ctx;



    public Session(Context ctx){

        this.ctx = ctx;

        prefs = ctx.getSharedPreferences("mypref", Context.MODE_PRIVATE);

        editor = prefs.edit();

    }



    public void setLoggedin(boolean logggedin){

        editor.putBoolean("loggedInmode",logggedin);

        editor.commit();

    }



    public boolean loggedin(){

        return prefs.getBoolean("loggedInmode", false);

    }
}
