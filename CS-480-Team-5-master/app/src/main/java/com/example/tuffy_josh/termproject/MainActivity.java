package com.example.tuffy_josh.termproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import  android.graphics.Typeface;
import android.widget.Button;

import static android.R.attr.type;

public class MainActivity extends AppCompatActivity {
    private Button builderButton;
    private Button classListButton;

    private Button campusMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builderButton = (Button)findViewById(R.id.builderButton);
        classListButton = (Button)findViewById(R.id.classListButton);

        campusMap = (Button)findViewById(R.id.campusMap);

        Intent i4=new Intent (this, animation.class);
        startActivity(i4);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
        builderButton.setTypeface(type);
        classListButton.setTypeface(type);

        campusMap.setTypeface(type);
    }

    public void scheduleBuilderClicked(View v){
        Intent i1 = new Intent(this,builder.class);
        startActivity(i1);
    }

    public void classListClicked(View v){
        Intent i2 = new Intent(this, webviewclasses.class);
        startActivity(i2);
    }

    public void campusMapClicked(View v){
        Intent i3 = new Intent(this, mapofcampus.class);
        startActivity(i3);
    }


}
