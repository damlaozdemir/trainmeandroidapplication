package com.dmlozdmr.trainme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayFoods extends AppCompatActivity {

    TextView foodnamevalue, calorievalue, proteinvalue, fatvalue, carbohydratevalue;

    private static final String URL = "http://trainme.net16.net/trainme/";
    RequestQueue requestQueue;
    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_foods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Information");


        foodnamevalue = (TextView) findViewById(R.id.foodnamevalue);
        calorievalue = (TextView) findViewById(R.id.calorievalue);
        proteinvalue = (TextView) findViewById(R.id.proteinvalue);
        fatvalue = (TextView) findViewById(R.id.fatvalue);
        carbohydratevalue = (TextView) findViewById(R.id.carbohydratevalue);




        requestQueue = Volley.newRequestQueue(this);



                request = new StringRequest(Request.Method.POST, URL+"foodinfobyname.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("foods");

                            for(int i=0; i<result.length(); i++){
                                JSONObject c = result.getJSONObject(i);

                                String foodname = c.getString("foodname");
                                String calories = c.getString("calories");
                                String protein = c.getString("protein");
                                String fat = c.getString("fat");
                                String carbohydrate = c.getString("carbohydrate");
                                foodnamevalue.setText(foodname);
                                calorievalue.setText(calories);
                                proteinvalue.setText(protein);
                                fatvalue.setText(fat);
                                carbohydratevalue.setText(carbohydrate);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams()throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("foodname", Diet.foodname.toString());


                        return hashMap;
                    }
                };
                requestQueue.add(request);




    }

}
