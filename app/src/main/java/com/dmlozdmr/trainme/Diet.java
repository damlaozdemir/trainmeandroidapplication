package com.dmlozdmr.trainme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Diet extends Activity {

    // List view
    ListView list_view;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;
    private static final String URL = "http://trainme.net16.net/trainme/";
    private RequestQueue requestQueue;
    private StringRequest request;

    // ArrayList for Listview
    ArrayList<String> foodnames = new ArrayList<String>();
    ArrayList<String> calories = new ArrayList<String>();
    ArrayList<String> proteins = new ArrayList<String>();
    ArrayList<String> fats = new ArrayList<String>();
    ArrayList<String> carbohydrates = new ArrayList<String>();
    ArrayList<HashMap<String, String>> productList;
    public static String foodname;
    public static String calorie;
    public static String protein;
    public static String fat;
    public static String carbohydrate;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfood);
        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, URL+"getfoodinfos.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("foods");

                    for(int i=0; i<result.length(); i++){
                        JSONObject c = result.getJSONObject(i);

                        String foodname = c.getString("foodname");
                        String calorie = c.getString("calories");
                        String protein = c.getString("protein");
                        String fat = c.getString("fat");
                        String carbohydrate = c.getString("carbohydrate");

                        foodnames.add(foodname);
                        calories.add(calorie);
                        proteins.add(protein);
                        fats.add(fat);
                        carbohydrates.add(carbohydrate);


                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.foodslist, R.id.product_name, foodnames);
                    list_view.setAdapter(adapter);


                    list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapter, View v, int position,
                                                long arg3) {
                            startActivity(new Intent(getApplicationContext(), DisplayFoods.class));

                            foodname = foodnames.get(position).toString();
                            foodname = adapter.getAdapter().getItem(position).toString();
                            calorie = calories.get(position).toString();
                            protein = proteins.get(position).toString();
                            fat = fats.get(position).toString();
                            carbohydrate = carbohydrates.get(position).toString();

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

        list_view = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);


        /**
         * Enabling Search Filter
         * */

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                Diet.this.adapter.getFilter().filter(cs.toString());


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                list_view.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                if (arg0.length() == 0) {
                    list_view.clearTextFilter();
                }
            }
        });
    }
}