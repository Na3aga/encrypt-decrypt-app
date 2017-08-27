package freelance.encrypt_decrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
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

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private ConstraintLayout mainLayer;
    private EditText topText;
    private TextView botText;
    private Button buttonMenu, buttonHelp;
    private int lastKeyType = 0;
    private final String LAST_KEY_TYPE = "last_key_type";
    private Dialog1 dialog1;
    private Dialog2 dialog2;
    private FrameLayout date$time,numSpinner;
    private TextInputLayout textPicker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        View recTop = (View) findViewById(R.id.rectangle_at_the_top);
        View recBot = (View) findViewById(R.id.rectangle_at_the_bot);
        recTop.getBackground().setAlpha(128);
        recBot.getBackground().setAlpha(128);

        TextView textView = (TextView) findViewById(R.id.label);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "COPRGTB.TTF");
        textView.setTypeface(typeFace);

        topText = (EditText) findViewById(R.id.textTop);
        botText = (TextView) findViewById(R.id.textBot);
        botText.setText("text android");

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
            case R.id.buttonMenu:
                dialog1.show(getFragmentManager(),"dlg1"); break;
            case R.id.buttonHelp:
                dialog2 = null;
                dialog2 = new Dialog2(this);
                dialog2.show(getFragmentManager(),"dlg2");break;
            case R.id.imageButtonPlay :
                try {
                    botText.setText(AESCrypt.encrypt(textPicker.getEditText().getText().toString(),topText.getText().toString()));
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.imageButtonSearch :
                try {
                    botText.setText(AESCrypt.decrypt(textPicker.getEditText().getText().toString(),topText.getText().toString()));
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }

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
