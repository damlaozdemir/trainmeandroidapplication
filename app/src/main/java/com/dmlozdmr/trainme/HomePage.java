package com.dmlozdmr.trainme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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


public class HomePage extends Fragment implements View.OnClickListener {
    private ImageButton beCoach;
    private ImageButton findCoach;
    private ImageButton survey;
    private ImageButton exercises;
    private ImageButton programs;
    private ImageButton diet;
    private static final String URL = "http://trainme.net16.net/trainMe/";
    private RequestQueue requestQueue;
    private StringRequest request;




    ArrayList<String> caloriesList = new ArrayList<String>();
    ArrayList<String> proteinList = new ArrayList<String>();
    ArrayList<String> fatList = new ArrayList();
    ArrayList<String> carbohydrateList = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_homepage, container, false);

        beCoach = (ImageButton) v.findViewById(R.id.beCoach);
        beCoach.setOnClickListener(this);


        survey = (ImageButton) v.findViewById(R.id.survey);
        survey.setOnClickListener(this);

        exercises = (ImageButton) v.findViewById(R.id.exercises);
        exercises.setOnClickListener(this);



        diet = (ImageButton) v.findViewById(R.id.diet);
        diet.setOnClickListener(this);

        Button logout = (Button) v.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        return v;
    }
    public static ArrayList<String> foodnameList = new ArrayList<String>();
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.beCoach:

                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;


            case R.id.survey:

                Intent intent3 = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent3);
                break;
            case R.id.exercises:

                Intent intent4 = new Intent(getActivity(), Exercises.class);
                startActivity(intent4);
                break;

            case R.id.diet:
                getFoods();
                Intent intent6 = new Intent(getActivity(), Diet.class);
                startActivity(intent6);
                break;
            case R.id.logout:

                Intent intent7 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent7);
                break;

        }
    }

    public void getFoods(){
        requestQueue = Volley.newRequestQueue(getContext());

        request = new StringRequest(Request.Method.POST, URL + "getfoodinfos.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("foods");

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject c = result.getJSONObject(i);

                        String foodName = c.getString("foodname");
                        String calories = c.getString("calories");
                        String protein = c.getString("protein");
                        String fat = c.getString("fat");
                        String carbohydrate = c.getString("carbohydrate");

                        foodnameList.add(foodName);
                        caloriesList.add(calories);
                        proteinList.add(protein);
                        fatList.add(fat);
                        carbohydrateList.add(carbohydrate);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

        };

        requestQueue.add(request);
        System.out.print(foodnameList);
    }
}