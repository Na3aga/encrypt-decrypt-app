package freelance.encrypt_decrypt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Nazar on 25.08.2017.
 */

public class MenuActivity extends AppCompatActivity{
    TextView textView;

    private final String LAST_KEY_TYPE = "last_key_type";
    private final String SETTING_1_KEY = "setting_1_key";
    private int setting1key = 0;
    private int setting1keyd = 0;
    private final String SETTING_2_KEY = "setting_2_key";
    private int setting2key = 0;
    private int setting2keyd = 0;
    private final String SETTING_3_KEY = "setting_3_key";
    private int setting3key = 0;
    private int setting3keyd = 0;
    Context context = null;
    private Switch switch1,switch2,switch3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status));
        scrollView.setVerticalScrollBarEnabled(false);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        ImageButton button1 = (ImageButton) findViewById(R.id.imageButtonGo1);
        ImageButton button2 = (ImageButton) findViewById(R.id.imageButtonGo2);
        button1.setColorFilter(Color.WHITE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Enigma Mac App Store - https://macappsto.re/ru/hNUEkb.m";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "enigma");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Enigma App Store - https://appsto.re/ru/qwFRhb.i";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "enigma");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
            }
        });
        button2.setColorFilter(Color.WHITE);
        TextView buttonCancel = (TextView) findViewById(R.id.buttonLeft);
        TextView buttonOk = (TextView) findViewById(R.id.buttonRight);
        TextView uniLink = (TextView) findViewById(R.id.textLink);
        uniLink.setLinksClickable(true);
        context = getApplicationContext();

        SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
        if(shref.getInt(SETTING_1_KEY,0)==1) {
            setting1key = 1;
            setting1keyd = 1;
            switch1.setChecked(true);
        }
        if(shref.getInt(SETTING_2_KEY,0)==1) {
            setting2key = 1;
            setting2keyd = 1;
            switch2.setChecked(true);
        }
        if(shref.getInt(SETTING_3_KEY,0)==1) {
            setting3key = 1;
            setting3keyd = 1;
            switch3.setChecked(true);
        }
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setting1keyd==1) {
                    switch1.setChecked(true);
                }else {
                    switch1.setChecked(false);
                }
                if(setting2keyd==1) {

                    switch2.setChecked(true);
                }else {
                    switch2.setChecked(false);
                }
                if(setting3keyd==1) {

                    switch3.setChecked(true);
                }else {
                    switch3.setChecked(false);
                }
                finish();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    setting1key = 1;

                } else {
                    setting1key = 0;
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    setting2key = 1;
                } else {
                    setting2key = 0;
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    setting3key = 1;
                } else {
                    setting3key = 0;
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        if (setting1key == 1) {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_1_KEY,1).apply();

        } else {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_1_KEY,0).apply();
        }
        if (setting2key == 1) {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_2_KEY,1).apply();

        } else {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_2_KEY,0).apply();
        }
        if (setting3key == 1) {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_3_KEY,1).apply();

        } else {
            SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
            shref.edit() .putInt(SETTING_3_KEY,0).apply();
        }
        context=null;
        super.finish();
        super.onDestroy();
    }
}
