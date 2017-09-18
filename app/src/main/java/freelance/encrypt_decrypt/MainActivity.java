package freelance.encrypt_decrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.util.Linkify;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener,SharedPreferences.OnSharedPreferenceChangeListener {

    private ConstraintLayout mainLayer;
    private EditText topText;
    private TextView botText;
    private Button buttonMenu, buttonHelp;
    private int lastKeyType = 0;
    private final String LAST_KEY_TYPE = "last_key_type";
    private final String SETTING_1_KEY = "setting_1_key";
    private int setting1key = 0;
    private final String SETTING_2_KEY = "setting_2_key";
    private int setting2key = 0;
    private final String SETTING_3_KEY = "setting_3_key";
    private int setting3key = 0;

    private Dialog1 dialog1;
    private Dialog2 dialog2;
    private FrameLayout date$time,numSpinner;
    private SingleDateAndTimePicker datePicker;
    private NumberPicker pick1,pick2,pick3;
    private TextInputLayout textPicker;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        View recTop = (View) findViewById(R.id.rectangle_at_the_top);
        View recBot = (View) findViewById(R.id.rectangle_at_the_bot);
        recTop.getBackground().setAlpha(128);
        recBot.getBackground().setAlpha(128);

        TextView textView = (TextView) findViewById(R.id.label);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "COPRGTB.TTF");
        textView.setTypeface(typeFace);


        topText = (EditText) findViewById(R.id.textTop);
        botText = (TextView) findViewById(R.id.textBot);

        ImageButton buttonAdd = (ImageButton) findViewById(R.id.imageButtonAdd);
        buttonAdd.setOnClickListener(this);
        buttonAdd.setColorFilter(Color.WHITE);
        buttonAdd.getBackground().setAlpha(0);
        ImageButton buttonSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        buttonSearch.setColorFilter(Color.WHITE);
        buttonSearch.getBackground().setAlpha(0);
        buttonSearch.setOnClickListener(this);
        ImageButton buttonPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        buttonPlay.setColorFilter(Color.WHITE);
        buttonPlay.setAlpha(144);
        buttonPlay.getBackground().setAlpha(0);
        buttonPlay.setOnClickListener(this);
        ImageButton buttonX = (ImageButton) findViewById(R.id.imageButtonX);
        buttonX.setColorFilter(Color.RED);
        buttonX.getBackground().setAlpha(0);
        buttonX.setOnClickListener(this);

        ImageButton buttonCopy = (ImageButton) findViewById(R.id.imageButtonCopy);
        buttonCopy.setOnClickListener(this);
        buttonCopy.setColorFilter(Color.WHITE);
        buttonCopy.setAlpha(124);
        buttonCopy.getBackground().setAlpha(0);
        ImageButton buttonShare = (ImageButton) findViewById(R.id.imageButtonSent);
        buttonShare.setOnClickListener(this);
        buttonShare.setColorFilter(Color.WHITE);
        buttonShare.getBackground().setAlpha(0);

        mainLayer = (ConstraintLayout) findViewById(R.id.cnstrnt);

        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        buttonMenu.getBackground().setAlpha(0);
        buttonMenu.setOnClickListener(this);
        buttonHelp = (Button) findViewById(R.id.buttonHelp);
        buttonHelp.getBackground().setAlpha(0);
        buttonHelp.setOnClickListener(this);

        date$time = (FrameLayout) findViewById(R.id.dateANDtime);
        datePicker= (SingleDateAndTimePicker) findViewById(R.id.singledate);
        datePicker.setSelectorColor(Color.TRANSPARENT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM y");
        datePicker.setDayFormatter(dateFormat);
        Date dateMin = new Date(70,0,0,0,0);
        Date dateMax = new Date(300,0,0,0,0);
        datePicker.setStepMinutes(1);
        datePicker.setMinDate(dateMin);
        datePicker.setMaxDate(dateMax);
        datePicker.setIsAmPm(false);
        mainLayer.removeView(date$time);
        numSpinner = (FrameLayout) findViewById(R.id.numPicker);
        pick1 = (NumberPicker) findViewById(R.id.picker1);
        pick1.setMinValue(0);
        pick1.setMaxValue(9);
        pick2 = (NumberPicker) findViewById(R.id.picker2);
        pick2.setMinValue(0);
        pick2.setMaxValue(9);
        pick3 = (NumberPicker) findViewById(R.id.picker3);
        pick3.setMinValue(0);
        pick3.setMaxValue(9);
        mainLayer.removeView(numSpinner);
        textPicker = (TextInputLayout) findViewById(R.id.textPicker);
        EditText editText = (EditText) findViewById(R.id.textKey);
        int maxLength = 6;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(FilterArray);
        mainLayer.removeView(textPicker);

        dialog1 = new Dialog1(this,date$time,numSpinner,textPicker);


        Context context = getApplicationContext();
        SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(context);
        lastKeyType = shref.getInt(LAST_KEY_TYPE,0);
        setting1key=shref.getInt(SETTING_1_KEY,0);
        setting2key=shref.getInt(SETTING_2_KEY,0);
        setting3key=shref.getInt(SETTING_3_KEY,0);
        switch (lastKeyType){
            case 1: mainLayer.addView(date$time);break;
            case 2: mainLayer.addView(numSpinner);break;
            case 3: mainLayer.addView(textPicker);break;
            case 0: lastKeyType = 2;mainLayer.addView(numSpinner);break;
        }
        shref.registerOnSharedPreferenceChangeListener(this);
        if (setting3key==1) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }else{
            buttonMenu.setAlpha(0);
            buttonHelp.setAlpha(0);
        }

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
            case R.id.buttonMenu:
                dialog1.show(getFragmentManager(),"dlg1");break;
            case R.id.buttonHelp:
                dialog2 = null;
                dialog2 = new Dialog2(this);
                dialog2.show(getFragmentManager(),"dlg2");break;
            case R.id.imageButtonPlay :
                try {
                    String key = "";
                    datePicker.setDefaultDate(datePicker.getMinDate());
                    switch (lastKeyType){
                        case 1:key=getDateKey();break;
                        case 2:key=getNumberKey();break;
                        case 3:key=getStrokeKey();break;
                    }
                    if(setting1key == 0 ) {
                        botText.setText(AESCrypt.encrypt(key, topText.getText().toString()));
                    }else if(setting1key == 1) {
                        botText.setText("enigma://" + AESCrypt.encrypt(key, topText.getText().toString()));
                    }
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.imageButtonSearch :
                try {
                String key = "";
                switch (lastKeyType){
                    case 1:key=getDateKey();break;
                    case 2:key=getNumberKey();break;
                    case 3:key=getStrokeKey();break;
                }
                String text = topText.getText().toString();
                    if(text.length()>8) {
                        String ls = text.substring(0, 9);
                        if (ls.contains("enigma://")) {
                            text = text.substring(9, text.length());
                        }
                    }
                    botText.setText(AESCrypt.decrypt(key,text));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.imageButtonX :
                topText.setText("");
                botText.setText("");

                break;
            case R.id.imageButtonSent :
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = botText.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "enigma");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "sent with"));

                break;

        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
       switch (key){
           case SETTING_1_KEY: setting1key = sharedPreferences.getInt(key,0);
               break;
           case SETTING_2_KEY: setting2key = sharedPreferences.getInt(key,0);
               if(setting2key==1){
                   Linkify.addLinks(botText, Linkify.ALL);
                   botText.setLinksClickable(true);
               }else if(setting2key==0){
                   botText.setLinksClickable(false);
               }
               break;
           case SETTING_3_KEY: setting3key = sharedPreferences.getInt(key,0);
               break;
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
        mainLayer = null;
        topText = null;
        botText = null;
        buttonMenu = null;
        buttonHelp = null;
        dialog1 = null;
        dialog2 = null;
        SharedPreferences shref = PreferenceManager.getDefaultSharedPreferences(this);
        shref.edit() .putInt(LAST_KEY_TYPE,lastKeyType).apply();
        super.onDestroy();
    }
    public void doPositiveClick(int value){
        lastKeyType = value;
    }
    public void doNegativeClick(){

    }
    private String getDateKey(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        String key = dateFormat.format(datePicker.getDate()).toString();
         //TODO
        return key;
    }
    private String getNumberKey(){
        String key = (pick1.getValue())+"-"+Integer.toString(pick2.getValue())+"-"+Integer.toString(pick3.getValue());
        return key;
    }
    private String getStrokeKey(){
        String key = textPicker.getEditText().getText().toString();
        return key;
    }

}
