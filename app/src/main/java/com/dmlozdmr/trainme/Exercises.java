package com.dmlozdmr.trainme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Exercises extends Activity implements OnClickListener {

    TextView abs, biceps, calves, chest, forearms, glutes, hamstrings,
            lats, lowerback, middleback, shoulders, traps, triceps, quads,
            beginner, intermediate, professional;
    Button btnClosePopup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        abs = (TextView)findViewById(R.id.abs);
        abs.setOnClickListener(this);

        biceps = (TextView)findViewById(R.id.biceps);
        biceps.setOnClickListener(this);

        calves = (TextView)findViewById(R.id.calves);
        calves.setOnClickListener(this);

        chest = (TextView)findViewById(R.id.chest);
        chest.setOnClickListener(this);

        forearms = (TextView)findViewById(R.id.forearms);
        forearms.setOnClickListener(this);

        glutes = (TextView)findViewById(R.id.glutes);
        glutes.setOnClickListener(this);

        hamstrings = (TextView)findViewById(R.id.hamstrings);
        hamstrings.setOnClickListener(this);

        lats = (TextView)findViewById(R.id.lats);
        lats.setOnClickListener(this);

        lowerback = (TextView)findViewById(R.id.lowerback);
        lowerback.setOnClickListener(this);

        middleback = (TextView)findViewById(R.id.middleback);
        middleback.setOnClickListener(this);

        shoulders = (TextView)findViewById(R.id.shoulders);
        shoulders.setOnClickListener(this);

        traps = (TextView)findViewById(R.id.traps);
        traps.setOnClickListener(this);

        triceps = (TextView)findViewById(R.id.triceps);
        triceps.setOnClickListener(this);

        quads = (TextView)findViewById(R.id.quads);
        quads.setOnClickListener(this);
    }
    private PopupWindow pwindo;
    private void initiatePopupWindow() {
        try {

            LayoutInflater inflater = (LayoutInflater) Exercises.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.screen_popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 500, 500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER_HORIZONTAL, 0, 0);
            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

            beginner = (TextView)findViewById(R.id.beginner);
            beginner.setOnClickListener(this);
            intermediate = (TextView)findViewById(R.id.intermediate);
            intermediate.setOnClickListener(this);
            professional = (TextView)findViewById(R.id.professional);
            professional.setOnClickListener(this);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnClickListener cancel_button_click_listener = new OnClickListener() {
        public void onClick(View v) {
            pwindo.dismiss();

        }
    };
    public static String clickedItem;
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.abs:
                clickedItem = "abdominals";
                break;
            case R.id.biceps:

                clickedItem = "biceps";

                break;
            case R.id.calves:

                clickedItem = "calves";

                break;
            case R.id.chest:

                clickedItem = "chest";

                break;
            case R.id.forearms:

                clickedItem = "forearms";

                break;
            case R.id.glutes:

                clickedItem = "glutes";

                break;
            case R.id.hamstrings:

                clickedItem = "hamstrings";

                break;
            case R.id.lats:

                clickedItem = "lats";

                break;
            case R.id.lowerback:

                clickedItem = "lowerback";

                break;
            case R.id.middleback:
                clickedItem = "middleback";


                break;
            case R.id.shoulders:

                clickedItem = "shoulders";

                break;
            case R.id.traps:

                clickedItem = "traps";

                break;
            case R.id.triceps:

                clickedItem = "triceps";

                break;
            case R.id.quads:

                clickedItem = "quadriceps";

                break;
        }
        startActivity(new Intent(getApplicationContext(), GetExercises.class));
    }
}