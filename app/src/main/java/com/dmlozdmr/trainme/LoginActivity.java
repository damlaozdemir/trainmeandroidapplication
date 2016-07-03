package com.dmlozdmr.trainme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by wın 7 on 08.01.2016.
 */
public class LoginActivity extends AppCompatActivity {

    private Button bSignin, bSignup;
    public static EditText etEmail, etPassword;
    private static final String URL = "http://trainme.net16.net/trainme/";
    private RequestQueue requestQueue;
    private StringRequest request;

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            new SimpleEula(this).show();

            etEmail = (EditText) findViewById(R.id.etEmail);
            etPassword = (EditText) findViewById(R.id.etPassword);

            bSignin = (Button) findViewById(R.id.bSignin);
            bSignup = (Button) findViewById(R.id.bSignup);

            requestQueue = Volley.newRequestQueue(this);

            bSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    request = new StringRequest(Request.Method.POST, URL+"login.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.names().get(0).equals("success")){
                                    Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Profile.class));
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
                            hashMap.put("email", etEmail.getText().toString());
                            hashMap.put("password", etPassword.getText().toString());
                            return hashMap;
                        }
                      };
                    requestQueue.add(request);
                }
            });


            bSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                }
            });

        }


}
