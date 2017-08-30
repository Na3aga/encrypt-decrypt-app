package freelance.encrypt_decrypt;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by na3aga on 07.08.17.
 */

public class Dialog1 extends DialogFragment implements View.OnClickListener {

    final Activity mainAct;
    final private FrameLayout date$time;
    final private FrameLayout numSpinner;
    final private TextInputLayout textPicker;
    private int keytype;


    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.activity_menu,null);
        // reference to the edittext



        builder.setView(v);



        v.findViewById(R.id.enigmaTitle).setAlpha(0.4f);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.argb(100,255,255,255)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(10);
        gd.setStroke(1, Color.argb(96,255,255,255));
        v.findViewById(R.id.keytype1).setBackground(gd);
        v.findViewById(R.id.keytype1).setOnClickListener(this);
        v.findViewById(R.id.keytype2).setBackground(gd);
        v.findViewById(R.id.keytype2).setOnClickListener(this);
        v.findViewById(R.id.keytype3).setBackground(gd);
        v.findViewById(R.id.keytype3).setOnClickListener(this);
        v.findViewById(R.id.buttonOk).setBackground(gd);
        v.findViewById(R.id.buttonOk).setOnClickListener(this);

        Dialog dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Dialog1(Activity activity,FrameLayout dt,FrameLayout ns,TextInputLayout tp) {
        mainAct = activity;
        date$time = dt;
        numSpinner = ns;
        textPicker = tp;
    }

    @Override
    public void onClick(View view) {
           switch (view.getId()){


               case R.id.keytype1:
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(date$time);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(numSpinner);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(textPicker);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).addView(date$time);
                   keytype =1;
                   ((MainActivity)getActivity()).doPositiveClick(keytype);
                   break;

               case R.id.keytype2:

                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(date$time);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(numSpinner);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(textPicker);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).addView(numSpinner);
                   keytype =2;
                   ((MainActivity)getActivity()).doPositiveClick(keytype);
                   break;
               case R.id.keytype3:

                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(date$time);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(numSpinner);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).removeView(textPicker);
                   ((ConstraintLayout)mainAct.findViewById(R.id.cnstrnt)).addView(textPicker);
                   keytype =3;
                   ((MainActivity)getActivity()).doPositiveClick(keytype);
                   break;
               case R.id.buttonOk:
                   ((MainActivity)getActivity()).doPositiveClick(keytype);
                   getDialog().dismiss();
                   break;

               default: break;
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
