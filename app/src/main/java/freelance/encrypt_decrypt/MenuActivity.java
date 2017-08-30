package freelance.encrypt_decrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Nazar on 25.08.2017.
 */

public class MenuActivity extends AppCompatActivity{
    TextView textView;

    public MenuActivity(TextView textView) {
        this.textView = textView;
    }

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
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    Linkify.addLinks(textView, Linkify.ALL);
                } else {

                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {

                } else {

                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                } else {

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
