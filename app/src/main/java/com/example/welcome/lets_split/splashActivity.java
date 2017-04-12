package com.example.welcome.lets_split;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {

    TextView tv;
    static final String[] COLORS =
            { AColor.B2, AColor.B3,AColor.B4, AColor.B5, AColor.B6, AColor.B1,};// colour array
    final int interval = 200;

    private int currentColor = 0;

    private void updateColor()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (currentColor > COLORS.length - 1)
                {
                    currentColor = 0;
                }
                tv.setTextColor(Color.parseColor(COLORS[currentColor]));
                currentColor++;
            }// end of run
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       // Typeface mytf=Typeface.createFromAsset(getAssets(),"COPRGTL.TTF");
       // tv=(TextView)findViewById(R.id.tv1);
//        tv.setTypeface(mytf);
//
//
//        Animation anim= AnimationUtils.loadAnimation(this,R.anim.move_up);
//        ImageView imageview=(ImageView) findViewById(R.id.splash);
//        imageview.setAnimation(anim);

        //tv.setTextColor(Color.parseColor("B0E1C5"));

//        new Thread(new Runnable()
//        {
//            public void run()
//            {
//                while (true)
//                {
//                    try
//                    {
//                        Thread.sleep(interval); // sleep for interval
//                    }
//                    catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    //updateColor();
//                } // end of  while true loop
//            }// end of run
//        }).start(); // end of runnable

        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(splashActivity.this,MainActivity.class));
                finish();
            }
        },4000);

    }
}


//new classs
class AColor
{
    final static String B1 = "#3E9E68"; // hex alpha_R_G_B
    final static String B2 = "#4D996E";
    final static String B3 = "#6FBA90";
    final static String B4 = "#6FBA90";
    final static String B5 = "#9DDAB8";
    final static String B6 = "#B0E1C5";
}
