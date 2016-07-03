package com.dmlozdmr.trainme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wın 7 on 10.04.2016.
 */
public class GetExercises extends Activity {
    private static final String URL = "http://trainme.net16.net/trainme/";
    private RequestQueue requestQueue;
    private StringRequest request;
    ListView list;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> muscles = new ArrayList<String>();
    ArrayList<String> ratings = new ArrayList<String>();
    ArrayList<String> equips = new ArrayList();
    ArrayList<String> levels = new ArrayList<String>();
    ArrayList<String> images = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();
    public static String muscle;
    public static String name;
    public static String equipment;
    public static String rating;
    public static String level;
    public static String image;
    public static String description;
    public static String imageurl;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercisesbymuscle);
        list = (ListView)findViewById(R.id.listView);
        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, URL+"exercisesbymusclegroup.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("exercises");

                            for(int i=0; i<result.length(); i++){
                                JSONObject c = result.getJSONObject(i);

                                String name = c.getString("name");
                                String muscle = c.getString("muscle");
                                String level = c.getString("level");
                                String rating = c.getString("rating");
                                String equipment = c.getString("equipment");
                                String image = c.getString("image");
                                String description = c.getString("description");

                                imageurl = URL + image ;
                                System.out.println(imageurl);
                                names.add(name);
                                muscles.add(muscle);
                                levels.add(level);
                                ratings.add(rating);
                                equips.add(equipment);
                                images.add(imageurl);
                                descriptions.add(description);
                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                        android.R.layout.simple_list_item_1, android.R.id.text1, names);
                                list.setAdapter(adapter);



                            }


                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                                            long arg3) {
                                        startActivity(new Intent(getApplicationContext(), DisplayExercises.class));


                                        muscle = muscles.get(position).toString();
                                        level = levels.get(position).toString();
                                        rating = ratings.get(position).toString();
                                        equipment = equips.get(position).toString();
                                        name = names.get(position).toString();
                                        image = images.get(position).toString();
                                        System.out.println(image);
                                        description = descriptions.get(position).toString();


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
                }){

            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("muscle", Exercises.clickedItem.toString());
                return hashMap;
            }
                };
                requestQueue.add(request);






    }
}
