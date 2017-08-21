package freelance.encrypt_decrypt;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by na3aga on 07.08.17.
 */

public class Dialog2 extends DialogFragment implements View.OnClickListener {

    Button htEnc,htDec;
    int height;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.dialog_help,null);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        v.findViewById(R.id.enigmaTitle).setAlpha(0.4f);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.argb(96,255,255,255)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(10);
        gd.setStroke(1, Color.argb(96,255,255,255));
        htEnc = (Button) v.findViewById(R.id.keytype1);
        htEnc.setSingleLine(false);
        v.findViewById(R.id.keytype1).setBackground(gd);
        v.findViewById(R.id.keytype1).setOnClickListener(this);
        v.findViewById(R.id.keytype2).setBackground(gd);
        v.findViewById(R.id.keytype2).setOnClickListener(this);
        v.findViewById(R.id.keytype3).setBackground(gd);
        v.findViewById(R.id.keytype3).setOnClickListener(this);
        v.findViewById(R.id.cancel).setBackground(gd);
        v.findViewById(R.id.cancel).setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Dialog2() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.keytype1:
                if(htEnc.getText() == getString(R.string.how_to_enc)){
                  htEnc.setText("how to encrypt");
                }else{
                htEnc.setText(R.string.how_to_enc);
                }
//                height = htEnc.getHeight();
//                Thread thread = new Thread(new MyRunnable());
//                thread.start();
                break;

            case R.id.keytype2:
                onDismiss(getDialog());
                break;
            case R.id.keytype3:

                onDismiss(getDialog());
                break;
            case R.id.cancel:
                getDialog().cancel();
                break;
            default: break;
        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            long delay = 2000;
            try {
                Thread.sleep(delay);
                Thread.sleep(10);
                height = htEnc.getHeight();
                htEnc.setHeight(height);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
