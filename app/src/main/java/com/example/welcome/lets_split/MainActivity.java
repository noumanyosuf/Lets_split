package com.example.welcome.lets_split;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.lang.String;

public class MainActivity extends AppCompatActivity {


    static Button Badd, Bdone, Bclear;
    static EditText ETname;
    static TextView TVcanvus;
    ImageButton b4;
    SeekBar sb;
    static int total_friends = 0;
    static String name[] = new String[20];
    static int NM[][] = new int[20][20];//name and money shared array
    static int amount[] = new int[20];
    String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Badd = (Button) findViewById(R.id.b_add);
        Bdone = (Button) findViewById(R.id.b_done);
        Bclear = (Button) findViewById(R.id.b_clear);
        ETname = (EditText) findViewById(R.id.name);
        sb = (SeekBar) findViewById(R.id.sb1);
        TVcanvus = (TextView) findViewById(R.id.tv_can);

        b4 = (ImageButton)findViewById(R.id.b5);
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"noumancse@gmail.com.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Lets Split");
                email.putExtra(Intent.EXTRA_TEXT, "Please enter your query/appreciation here");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client to your query/appreciation:"));
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                TVcanvus.setTextSize(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Bclear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < total_friends; i++) {
                    name[i] = "";
                    for (int j = 0; j < total_friends; j++) {
                        NM[i][j] = 0;
                    }
                }
                ETname.setText("");
                ETname.setHint("Name");
                TVcanvus.setText("Name of friends added");
                total_friends = 0;
            }
        });

        Bdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check id total_friends is not less than 2 and throw a user defined excetion **************

                if(total_friends<2)
                {
                    Toast.makeText(MainActivity.this, "please add more friends", Toast.LENGTH_LONG).show();
                }

                else {

                    for(int i=0;i<total_friends;i++)
                    {
                        for(int j=0;j<total_friends;j++)
                        {
                           NM[i][j]=0;
                        }
                    }

                    Intent secondpage = new Intent(MainActivity.this, second_page.class);
                    startActivity(secondpage);
                }
            }
        });

        Badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                s1 = (ETname.getText().toString()).toUpperCase();
                if (s1.length()!= 0) {

                    boolean f=false;
                    if(total_friends!=0)
                    {
                        for(int i=0;i<total_friends;i++)
                        {
                            if(name[i] == s1){
                                f=true;
                                f=true;
                                Toast.makeText(MainActivity.this, "This name already present in the list", Toast.LENGTH_LONG).show();
                                break;}
                        }
                    }

                    if(f == false) {
                        name[total_friends] = s1;
                        TVcanvus.setText(TVcanvus.getText() + "\n" + s1);
                        total_friends++;
                        ETname.setText("");
                        ETname.setHint("Add another name");
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
