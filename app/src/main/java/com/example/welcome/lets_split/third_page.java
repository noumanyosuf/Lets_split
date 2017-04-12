package com.example.welcome.lets_split;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class third_page extends AppCompatActivity implements View.OnClickListener {

    SeekBar sb;
    Button bmain;
    TextView TVdis;
    CheckBox cb;
    Button share;
    File imagePath;


    int round_up(int num)
    {
        int rem=num%10;
        if(rem<5)
            return (num-rem);
        else
            return (num+(10-rem));
    }
    void calculate()
    {
        for(int i=0;i<MainActivity.total_friends;i++)
        {
            for(int j=0;j<MainActivity.total_friends;j++)
            {
                if(i==j) continue;
                if(MainActivity.NM[i][j]==0 || MainActivity.NM[j][i]==0)continue;
                if(MainActivity.NM[i][j]>MainActivity.NM[j][i])
                {
                    MainActivity.NM[i][j]=MainActivity.NM[i][j]-MainActivity.NM[j][i];
                    MainActivity.NM[j][i]=0;
                }
                else
                {
                    MainActivity.NM[j][i]=MainActivity.NM[j][i]-MainActivity.NM[i][j];
                    MainActivity.NM[i][j]=0;
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);



        bmain=(Button) findViewById(R.id.main);
        TVdis=(TextView)findViewById(R.id.dis);
        sb = (SeekBar) findViewById(R.id.sb1);
        cb=(CheckBox)findViewById(R.id.cb);
        cb.setOnClickListener(this);


        int count=0;
        {
            for (int i = 0; i < MainActivity.total_friends; i++) {
                for (int j = 0; j < MainActivity.total_friends; j++) {
                    if (MainActivity.NM[i][j] != 0) count++;
                }
            }

            if (count != 0){
                for (int i = 0; i < MainActivity.total_friends; i++) {

                    calculate();

                    for (int j = 0; j < MainActivity.total_friends; j++) {
                        if (MainActivity.NM[i][j] != 0) {
                                TVdis.setText(TVdis.getText() + "\n" + MainActivity.name[j] + " have to give to " + MainActivity.name[i] + " amount " + round_up(MainActivity.NM[i][j]));
                        }
                    }
                }
        }
            else
            {
                TVdis.setText("Everyone's dues are clear.No one have to give anything to anyone.");
            }


        }

        bmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < MainActivity.total_friends; i++) {
                    MainActivity.name[i] = "";
                    for (int j = 0; j < MainActivity.total_friends; j++) {
                        MainActivity.NM[i][j] = 0;
                    }
                }

                MainActivity.ETname.setText("");
                MainActivity.ETname.setHint("Name");
                MainActivity.TVcanvus.setText("Name of friends added");
                MainActivity.total_friends = 0;
                     Intent secondpage = new Intent(third_page.this, MainActivity.class);
                    startActivity(secondpage);
            }
        });



        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                TVdis.setTextSize(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });




    }

    @Override
    public void onClick(View v) {
        CheckBox t=(CheckBox) v;
        if(t.isChecked())
        {
            TVdis.setText("");
            int count=0;
            {
                for (int i = 0; i < MainActivity.total_friends; i++) {
                    for (int j = 0; j < MainActivity.total_friends; j++) {
                        if (MainActivity.NM[i][j] != 0) count++;
                    }
                }

                if (count != 0){
                    for (int i = 0; i < MainActivity.total_friends; i++) {

                        calculate();

                        for (int j = 0; j < MainActivity.total_friends; j++) {
                            if (MainActivity.NM[i][j] != 0) {
                                TVdis.setText(TVdis.getText() + "\n" + MainActivity.name[j] + " have to give to " + MainActivity.name[i] + " amount " + MainActivity.NM[i][j]);
                            }
                        }
                    }
                }
                else
                {
                    TVdis.setText("Everyone's dues are clear.No one have to give anything to anyone.");
                }


            }

        }
        else
        {
            TVdis.setText("");
            int count=0;
            {
                for (int i = 0; i < MainActivity.total_friends; i++) {
                    for (int j = 0; j < MainActivity.total_friends; j++) {
                        if (MainActivity.NM[i][j] != 0) count++;
                    }
                }

                if (count != 0){
                    for (int i = 0; i < MainActivity.total_friends; i++) {

                        calculate();

                        for (int j = 0; j < MainActivity.total_friends; j++) {
                            if (MainActivity.NM[i][j] != 0) {
                                TVdis.setText(TVdis.getText() + "\n" + MainActivity.name[j] + " have to give to " + MainActivity.name[i] + " amount " + round_up(MainActivity.NM[i][j]));
                            }
                        }
                    }
                }
                else
                {
                    TVdis.setText("Everyone's dues are clear.No one have to give anything to anyone.");
                }


            }
        }

        share=(Button) findViewById(R.id.b1);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
            }
        });
    }


    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "final dues of the Lets Split";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
