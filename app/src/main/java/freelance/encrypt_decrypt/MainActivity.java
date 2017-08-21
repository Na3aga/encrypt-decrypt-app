package freelance.encrypt_decrypt;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    public ConstraintLayout mainLayer;
    EditText topText,botText;
    Button buttonMenu, buttonHelp;
    int lastKeyType = 0;
    final String LAST_KEY_TYPE = "last_key_type";
    Dialog1 dialog1;
    Dialog2 dialog2;
    FrameLayout date$time,numSpinner;
    TextInputLayout textPicker;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View recTop = (View) findViewById(R.id.rectangle_at_the_top);
        View recBot = (View) findViewById(R.id.rectangle_at_the_bot);
        recTop.getBackground().setAlpha(128);
        recBot.getBackground().setAlpha(128);

        TextView textView = (TextView) findViewById(R.id.label);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "COPRGTB.TTF");
        textView.setTypeface(typeFace);

        topText = (EditText) findViewById(R.id.textTop);
        botText = (EditText) findViewById(R.id.textBot);

        ImageButton buttonAdd = (ImageButton) findViewById(R.id.imageButtonAdd);
        buttonAdd.setOnClickListener(this);
        buttonAdd.setColorFilter(Color.WHITE);
        buttonAdd.getBackground().setAlpha(0);
        ImageButton buttonSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        buttonSearch.setColorFilter(Color.WHITE);
        buttonSearch.getBackground().setAlpha(0);
        ImageButton buttonPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        buttonPlay.setColorFilter(Color.WHITE);
        buttonPlay.setAlpha(148);
        buttonPlay.getBackground().setAlpha(0);
        ImageButton buttonX = (ImageButton) findViewById(R.id.imageButtonX);
        buttonX.setColorFilter(Color.RED);
        buttonX.getBackground().setAlpha(0);
        ImageButton buttonCopy = (ImageButton) findViewById(R.id.imageButtonCopy);
        buttonCopy.setOnClickListener(this);
        buttonCopy.setColorFilter(Color.WHITE);
        buttonCopy.setAlpha(124);
        buttonCopy.getBackground().setAlpha(0);
        ImageButton buttonCopySend = (ImageButton) findViewById(R.id.imageButtonSent);
        buttonCopySend.setColorFilter(Color.WHITE);
        buttonCopySend.setAlpha(164);
        buttonCopySend.getBackground().setAlpha(0);

        mainLayer = (ConstraintLayout) findViewById(R.id.cnstrnt);

        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        buttonMenu.getBackground().setAlpha(0);
        buttonMenu.setOnClickListener(this);
        buttonHelp = (Button) findViewById(R.id.buttonHelp);
        buttonHelp.getBackground().setAlpha(0);
        buttonHelp.setOnClickListener(this);

        date$time = (FrameLayout) findViewById(R.id.dateANDtime);
        SingleDateAndTimePicker dateAndTimePicker = (SingleDateAndTimePicker) findViewById(R.id.singledate);
        dateAndTimePicker.setSelectorColor(Color.TRANSPARENT);
        mainLayer.removeView(date$time);
       numSpinner = (FrameLayout) findViewById(R.id.numPicker);
        NumberPicker picker1,picker2,picker3;
        picker1 = (NumberPicker) findViewById(R.id.picker1);
        picker1.setMinValue(0);
        picker1.setMaxValue(9);
        picker2 = (NumberPicker) findViewById(R.id.picker2);
        picker2.setMinValue(0);
        picker2.setMaxValue(9);
        picker3 = (NumberPicker) findViewById(R.id.picker3);
        picker3.setMinValue(0);
        picker3.setMaxValue(9);
        mainLayer.removeView(numSpinner);
        textPicker = (TextInputLayout) findViewById(R.id.textPicker);
        EditText editText = (EditText) findViewById(R.id.textKey);
        int maxLength = 6;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(FilterArray);
        mainLayer.removeView(textPicker);

        dialog1 = new Dialog1(this,date$time,numSpinner,textPicker);
        dialog2 = new Dialog2();

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.show(getFragmentManager(),"dlg1");
            }
        });

        SharedPreferences shref = getSharedPreferences("encdec",MODE_PRIVATE);
        lastKeyType = shref.getInt(LAST_KEY_TYPE,0);
        switch (lastKeyType){
            case 1: mainLayer.addView(date$time);break;
            case 2: mainLayer.addView(numSpinner);break;
            case 3: mainLayer.addView(textPicker);break;
            default: mainLayer.addView(numSpinner);break;
        }

        Thread thread = new Thread(new MyRunnable());
        thread.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonAdd :
                ClipboardManager clipboard1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                String pasteData = "";

                // If it does contain data, decide if you can handle the data.
                if (!(clipboard1.hasPrimaryClip())) {



                } else if (!(clipboard1.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {

                    // since the clipboard has data but it is not plain text

                } else {

                    //since the clipboard contains plain text.
                    ClipData.Item item = clipboard1.getPrimaryClip().getItemAt(0);

                    // Gets the clipboard as text.
                    pasteData = item.getText().toString();

                }
                topText.setText(pasteData); break;
            case R.id.imageButtonCopy :
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("your_text_to_be_copied", botText.getText());
                clipboard.setPrimaryClip(clip);
                Toast toast = Toast.makeText(this, "Text coppied to clipboard!", Toast.LENGTH_SHORT);
                toast.show(); break;
//            case R.id.buttonMenu: dialog1.show(getFragmentManager(),"dlg1"); break;
            case R.id.buttonHelp: dialog2.show(getFragmentManager(),"dlg2");break;


        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
              long delay = 2000;
            try {
                Thread.sleep(delay);
                AlphaAnimation an = new AlphaAnimation(1.0f,0.0f);
                an.setDuration(1000);
                runOnUiThread(new alphaStart());
                Thread.sleep(1000);
                runOnUiThread(new alphaEnd());


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private class alphaStart implements Runnable {

        @Override
        public void run() {

                AlphaAnimation an = new AlphaAnimation(1.0f,0.0f);
                an.setDuration(1000);
                buttonMenu.startAnimation(an);
                buttonHelp.startAnimation(an);

        }
    }

    private class alphaEnd implements Runnable {

        @Override
        public void run() {

            buttonMenu.setAlpha(0);
            buttonHelp.setAlpha(0);

        }
    }
    @Override
    protected void onDestroy() {
        if(date$time.isShown()){
            lastKeyType = 1;
        }else if(numSpinner.isShown()){
            lastKeyType =2;
        }else if(textPicker.isShown()){
            lastKeyType = 3;
        }
        mainLayer = null;
        topText = null;
        botText = null;
        buttonMenu = null;
        buttonHelp = null;
        dialog1 = null;
        dialog2 = null;
        SharedPreferences shref = getSharedPreferences("encdec",MODE_PRIVATE);
        SharedPreferences.Editor ed = shref.edit();
        ed.putInt(LAST_KEY_TYPE,lastKeyType);
        ed.apply();
        super.onDestroy();
    }

}
