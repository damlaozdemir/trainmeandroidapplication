package com.dmlozdmr.trainme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayExercises extends AppCompatActivity {

    TextView muscle, equipment, rating, level, description;
    ImageView imageView ,imageView2;

    RatingBar ratingBar;
    private static final String URL = "http://trainme.net16.net/trainme/";
    RequestQueue requestQueue;
    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercises);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(GetExercises.name);


        muscle = (TextView) findViewById(R.id.muscle);
        equipment = (TextView) findViewById(R.id.equipment);
        rating = (TextView) findViewById(R.id.rating);
        level = (TextView) findViewById(R.id.level);
        description = (TextView) findViewById(R.id.description);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        /*Picasso.with(getApplicationContext())
                .load(GetExercises.image)
                 //.resize(300, 300)                        // optional
                .into(image);*/
     ratingBar.setRating(Float.parseFloat(GetExercises.rating));

        muscle.setText(GetExercises.muscle);
        equipment.setText(GetExercises.equipment);
        level.setText(GetExercises.level);
        rating.setText(" "+GetExercises.rating);
        description.setText(GetExercises.description);


        requestQueue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                request = new StringRequest(Request.Method.POST, URL+"adduserexercises.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Snackbar.make(view, "Added to the programs", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }else{
                                Toast.makeText(getApplicationContext(), "Error " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("userid", "1");
                        hashMap.put("exerciseid", "2");
                        hashMap.put("programid", "5");

                        return hashMap;
                    }
                };
                requestQueue.add(request);

            }
        });
    }

}
