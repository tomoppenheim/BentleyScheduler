package com.example.tuffy_josh.termproject;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class animation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatexml);

        ImageView img = (ImageView) findViewById(R.id.simple_anim);
        img.setBackgroundResource(R.drawable.simple_animation);

        animation.AnimationRoutine1 task1 = new animation.AnimationRoutine1();
        animation.AnimationRoutine2 task2 = new animation.AnimationRoutine2();

        Timer t = new Timer();
        t.schedule(task1, 2000);
        Timer t2 = new Timer();
        t2.schedule(task2, 6000);

    }
    class AnimationRoutine1 extends TimerTask {

        @Override
        public void run() {
            ImageView img = (ImageView) findViewById(R.id.simple_anim);
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
            frameAnimation.start();
        }
    }

    class AnimationRoutine2 extends TimerTask {

        @Override
        public void run() {
            ImageView img = (ImageView) findViewById(R.id.simple_anim);
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
            frameAnimation.stop();
            finish();

        }
    }
}
