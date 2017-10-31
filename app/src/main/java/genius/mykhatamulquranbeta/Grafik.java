package genius.mykhatamulquranbeta;


import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import genius.mykhatamulquranbeta.getset.AngkaGetSet;

public class Grafik extends MainActivity {

    BarChart barChart;
    private List<AngkaGetSet> angkaGetSetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);
        barChart = (BarChart) findViewById(R.id.bargraph);
        angkaGetSetList = new  ArrayList <>() ;
/*
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f, 0));
        barEntries.add(new BarEntry(34f, 1));
        barEntries.add(new BarEntry(19f, 2));
        barEntries.add(new BarEntry(25f, 3));
        barEntries.add(new BarEntry(11f, 4));
        barEntries.add(new BarEntry(20f, 5));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Ahad");
        theDates.add("Senin");
        theDates.add("Selasa");
        theDates.add("Rabu");
        theDates.add("Kamis");
        theDates.add("Jumat");

        BarData theData = new BarData(theDates, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
*/
loadRecycle();
    }
    private void loadRecycle(){
        String url = "https://alkhawarizmi.000webhostapp.com/MySonSchool/MySonPhp/SelectTest.php?hari=Koala";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                JSONObject jsonObject = null;
                try {
                    ArrayList<BarEntry> barEntries = new ArrayList<>();
                    ArrayList<String> theDates = new ArrayList<>();
                    jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("record");
                    for(int i = 0; i < array.length(); i ++ ){
                        JSONObject o = array.getJSONObject(i);
                        barEntries.add(new BarEntry(Integer.parseInt( o.getString("JumlahHafal")), i));
                        theDates.add( o.getString("NamaHari"));
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
                    BarData theData = new BarData(theDates, barDataSet);
                    barChart.setData(theData);
                    barChart.setTouchEnabled(true);
                    barChart.setDragEnabled(true);
                    barChart.setScaleEnabled(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Data Loaded", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Grafik.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }



}





