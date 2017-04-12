package com.example.welcome.lets_split;

import java.util.*;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class second_page extends AppCompatActivity {

    boolean extrashare=false;
    TextView dn;//display_name
    EditText ETamount;//amount
    LinearLayout sv;//sv
    Button bnext,baddshare;//cal,next
    CheckBox cb;
    String s1;int scounter=0;
    int shared[]=new int[20];
    //int count= 0;
    void remove_duplicate()
    {
        int temp[]=new int[MainActivity.total_friends];
        int j=0;
        for(int i=0;i<MainActivity.total_friends;i++)
        {
            if(shared[i]==1)
            {
                temp[j]=i;j++;
            }
        }
        for(int i=0;i<MainActivity.total_friends;i++)
        {
            shared[i]=0;
        }
        for(int i=0;i<scounter;i++)
        {
            shared[i]=temp[i];
        }
    }
    void set_value_NM(int frnd,int amount_shared)
    {
            for(int j=0;j<scounter;j++)
            {
                if(frnd!=shared[j])
                MainActivity.NM[frnd][shared[j]] = MainActivity.NM[frnd][shared[j]] + (amount_shared / scounter);
            }
        //MainActivity.NM[frnd][frnd]=0;
    }

    void activity(final int f) {
        dn = (TextView) findViewById(R.id.display_name);
        ETamount = (EditText) findViewById(R.id.amount);
        sv = (LinearLayout) findViewById(R.id.ll);
        baddshare=(Button)findViewById(R.id.add_and_share);
        bnext = (Button) findViewById(R.id.next);

        dn.setText(MainActivity.name[f] + " share");
        if (f == MainActivity.total_friends-1) {
            bnext.setText("Calculate");
        }


            ArrayList<String> al = new ArrayList<String>();

            for (int i = 0; i < MainActivity.total_friends; i++) {
                al.add(MainActivity.name[i]);
            }
            for (int i = 0; i < MainActivity.total_friends; i++) {
                cb = new CheckBox(this);
                cb.setId(i);
                cb.setText(al.get(i));
                cb.setOnClickListener(getOnClickDoSomething(cb));
                sv.addView(cb);
            }


        baddshare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                s1 = ETamount.getText().toString();
                try {
                    MainActivity.amount[MainActivity.total_friends] = (java.lang.Integer.parseInt(s1));
                    if (scounter == 0 && MainActivity.amount[MainActivity.total_friends]!=0)
                        Toast.makeText(second_page.this, "Please chose checkbox to share the amount", Toast.LENGTH_LONG).show();
                    else {

                        if (s1.length()!=0) {

                            extrashare=true;

                            remove_duplicate();
                            set_value_NM(f,java.lang.Integer.parseInt(s1));

                            for(int i=0;i<scounter;i++){shared[i]=0;}
                            scounter=0;
                            sv.removeAllViews();
                            ETamount.setText("");
                            ETamount.setHint("Amount");
                            dn.setText(MainActivity.name[f] + " share");
                            activity(f);
                            return;

                        } else {
                            Toast.makeText(second_page.this, "Amount should a number", Toast.LENGTH_LONG).show();
                        }
                        //********************
                    }
                }
                catch (Exception e) {
                    Toast.makeText(second_page.this, "Amount should a number", Toast.LENGTH_LONG).show();
                                    }
            }
        });


        //----------------------------------------------------------------------------------------
        bnext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(extrashare==false) {
                    s1 = ETamount.getText().toString();
                    try {
                        MainActivity.amount[MainActivity.total_friends] = (java.lang.Integer.parseInt(s1));
                        if (scounter == 0 && MainActivity.amount[MainActivity.total_friends] != 0)
                            Toast.makeText(second_page.this, "Please chose checkbox to share the amount", Toast.LENGTH_LONG).show();
                        else {

                            if (s1.length()!=0) {

                                // do the calculation and refresh the page to update the value of i
                                remove_duplicate();
                                set_value_NM(f, java.lang.Integer.parseInt(s1));

                                if (f == MainActivity.total_friends - 1) {
                                    //open new intent and display the reults.done.


                                    Intent third_page = new Intent(second_page.this, third_page.class);
                                    startActivity(third_page);

                                } else {
                                    for (int i = 0; i < scounter; i++) {
                                        shared[i] = 0;
                                    }
                                    scounter = 0;
                                    sv.removeAllViews();
                                    ETamount.setText("");
                                    int x = f;
                                    x++;
                                    extrashare=false;
                                    dn.setText(MainActivity.name[x] + " share");
                                    activity(x);
                                    return;
                                }
                            } else {
                                Toast.makeText(second_page.this, "Amount should a number", Toast.LENGTH_LONG).show();
                            }
                            //********************
                        }
                    } catch (Exception e) {
                        Toast.makeText(second_page.this, "Amount should a number", Toast.LENGTH_LONG).show();
                    }
                }
                else if(extrashare==true)
                {
                    s1 = ETamount.getText().toString();
                    if(s1.length()!=0) {
                        try{
                        if (scounter != 0) {

                            MainActivity.amount[MainActivity.total_friends] = (java.lang.Integer.parseInt(s1));

                            remove_duplicate();

                            set_value_NM(f, java.lang.Integer.parseInt(s1));
                            if (f == MainActivity.total_friends - 1) {
                                //open new intent and display the reults.done.
                                Intent third_page = new Intent(second_page.this, third_page.class);
                                startActivity(third_page);
                            } else {
                                for (int i = 0; i < scounter; i++) {
                                    shared[i] = 0;
                                }
                                scounter = 0;
                                sv.removeAllViews();
                                ETamount.setText("");
                                int x = f;
                                x++;
                                extrashare=false;
                                dn.setText(MainActivity.name[x] + " share");
                                activity(x);
                                return;
                            }


                        } else {
                            Toast.makeText(second_page.this, "Check atleast one friend name to share this amount", Toast.LENGTH_LONG).show();
                        }
                          }
                        catch (Exception e)
                        {
                            Toast.makeText(second_page.this, "Amount should be a number", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        //amount is empty no need tto do anything just go to next name with updated value
                        if (f == MainActivity.total_friends - 1) {
                            //open new intent and display the reults.done.
                            Intent third_page = new Intent(second_page.this, third_page.class);
                            startActivity(third_page);
                        } else {
                            for (int i = 0; i < scounter; i++) {
                                shared[i] = 0;
                            }
                            scounter = 0;
                            sv.removeAllViews();
                            ETamount.setText("");
                            int x = f;
                            x++;
                            dn.setText(MainActivity.name[x] + " share");
                            activity(x);
                            return;
                        }
                    }

                }

            }
        });
    }
    //----------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

            activity(0);

    }//end of oncreate


    int return_index(String name)
    {
        int i=0;
        for(;i<MainActivity.total_friends;i++)
        {
            if(MainActivity.name[i]==name)
            {
                break;
            }
        }
        return i;
    }

    View.OnClickListener getOnClickDoSomething(final Button  button)
    {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int r=return_index(button.getText().toString());

                if(shared[r]==0){shared[r]=1;scounter++;}
                else if(shared[r]==1){shared[r]=0;scounter--;}

                //shared[scounter]=return_index(button.getText().toString());scounter++;
                //System.out.println("check item is "+button.getText().toString()+"and shared by is"+ button.getId()+"     "+button.getText().toString());
            }
        };
    }
}
