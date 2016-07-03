package com.dmlozdmr.trainme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ProfilePage extends Fragment implements View.OnClickListener{
    private EditText etName, etSurname, etEmail, etAge, etPassword;
    Button bsave, buttonChoose, buttonUpload;
    EditText editText;
    private static final String URL = "http://trainme.net16.net/trainMe/";
    private RequestQueue requestQueue;
    private StringRequest request;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private String UPLOAD_URL ="http://trainme.net16.net/trainme/upload.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_profilepage, container, false);

        //bsave = (Button) v.findViewById(R.id.bsave);
        buttonChoose = (Button) v.findViewById(R.id.buttonChoose);
        buttonUpload = (Button) v.findViewById(R.id.buttonUpload);
        imageView = (ImageView) v.findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        etName = (EditText) v.findViewById(R.id.etName);
        etSurname = (EditText) v.findViewById(R.id.etSurname);
        etEmail = (EditText) v.findViewById(R.id.etEmail);
        etAge = (EditText) v.findViewById(R.id.etAge);
        //etPassword = (EditText) v.findViewById(R.id.etPassword);


        editText = (EditText) v.findViewById(R.id.editText);

        return v;

    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void uploadImage(){
        requestQueue = Volley.newRequestQueue(getContext());
        //Showing the progress dialog

        //final ProgressDialog loading = ProgressDialog.show(getContext(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        //loading.dismiss();
                        //Showing toast message of the response

                        Toast.makeText(getContext(), s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        //loading.dismiss();

                        //Showing toast
                        Toast.makeText(getContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String

                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = editText.getText().toString().trim();

                //Creating parameters
                HashMap<String, String> hashMap = new HashMap<String, String>();

                //Adding parameters
                hashMap.put(KEY_IMAGE, image);
                hashMap.put(KEY_NAME, name);

                //returning parameters
                return hashMap;
            }
        };



        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v == buttonChoose){
            showFileChooser();
        }

        if(v == buttonUpload){
            uploadImage();
        }
    }
}

