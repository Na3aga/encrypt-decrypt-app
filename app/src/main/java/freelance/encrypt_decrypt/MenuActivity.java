package freelance.encrypt_decrypt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
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
    private final String SETTING_2_KEY = "setting_2_key";
    private int setting2key = 0;
    private final String SETTING_3_KEY = "setting_3_key";
    private int setting3key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setVerticalScrollBarEnabled(false);
        Switch switch1,switch2,switch3;
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        final Context context = getApplicationContext();
        SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
        if(shref.getInt(SETTING_1_KEY,0)==1) {
            switch1.setChecked(true);
        }
        if(shref.getInt(SETTING_2_KEY,0)==1) {
            switch2.setChecked(true);
        }
        if(shref.getInt(SETTING_3_KEY,0)==1) {
            switch3.setChecked(true);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
//                    Linkify.addLinks(textView, Linkify.ALL);
                    SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_1_KEY,1).apply();

                } else {
                    SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_1_KEY,0).apply();
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_2_KEY,1).apply();
                } else {
                    SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_2_KEY,0).apply();
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_3_KEY,1).apply();
                } else {
                    SharedPreferences shref =PreferenceManager.getDefaultSharedPreferences(context);
                    shref.edit() .putInt(SETTING_3_KEY,0).apply();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.finish();
        super.onDestroy();
    }
}
