package freelance.encrypt_decrypt;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by na3aga on 07.08.17.
 */

public class Dialog2 extends DialogFragment implements View.OnClickListener {

    FrameLayout htEnc,htDec;
    Button button11,button21;
    TextView button12,button22;
    Context activityC;
    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_help,null);
        // reference to the edittext



        builder.setView(v);
        v.findViewById(R.id.enigmaTitle).setAlpha(0.4f);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.argb(100,255,255,255)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(10);
        gd.setStroke(1, Color.argb(96,255,255,255));

        htEnc = (FrameLayout) v.findViewById(R.id.htEnc);
        htDec = (FrameLayout) v.findViewById(R.id.htDec);

        button11 = (Button) v.findViewById(R.id.keytype11);
        button11.setSingleLine(false);
        button11.setBackground(gd);
        button11.setOnClickListener(this);
        button12 = (TextView) v.findViewById(R.id.keytype12);
        button12.setSingleLine(false);
        button12.setBackground(new ColorDrawable(Color.TRANSPARENT));
        button12.setOnClickListener(this);

        htEnc.removeView(button12);

        button21 = (Button) v.findViewById(R.id.keytype21);
        button21.setSingleLine(false);
        button21.setBackground(gd);
        button21.setOnClickListener(this);
        button22 = (TextView) v.findViewById(R.id.keytype22);
        button22.setSingleLine(false);
        button22.setBackground(new ColorDrawable(Color.TRANSPARENT));
        button22.setOnClickListener(this);

        htDec.removeView(button22);

        Button settings = (Button) v.findViewById(R.id.keytype3);
        settings.setSingleLine(false);
        settings.setBackground(gd);
        settings.setOnClickListener(this);
        Button cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setSingleLine(false);
        cancel.setBackground(gd);
        cancel.setOnClickListener(this);
        Dialog dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Dialog2(Context activity) {
      activityC = activity;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.keytype11:
                htEnc.removeView(button11);
                htEnc.addView(button12);
                break;

            case R.id.keytype12:
                htEnc.removeView(button12);
                htEnc.addView(button11);
                break;

            case R.id.keytype21:
                htDec.removeView(button21);
                htDec.addView(button22);
                break;

            case R.id.keytype22:
                htDec.removeView(button22);
                htDec.addView(button21);
                break;

            case R.id.keytype3:
                Intent intent = new Intent(activityC,MenuActivity.class);
                startActivity(intent);
                getDialog().cancel();
                break;
            case R.id.cancel:
                getDialog().cancel();
                break;
            default: break;
        }
    }

//    private class MyRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(10);
//                height = htEnc.getHeight();
//                htEnc.setHeight(height);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        htEnc = null;
        htDec = null;
        button11=null;
        button21=null;
        button12=null;
        button22=null;
        activityC = null;
        super.onCancel(dialog);
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        htEnc = null;
        htDec = null;
        button11=null;
        button21=null;
        button12=null;
        button22=null;
        activityC = null;
        activityC = null;
        super.onCancel(dialog);
    }
}
