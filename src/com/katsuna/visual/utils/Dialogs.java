package com.katsuna.visual.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by christosmitatakis on 4/8/17.
 */

public class Dialogs {

    public static AlertDialog ShowAlertDialog(Activity activity, String title, String message, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(Html.fromHtml("<b>"+ message + " cm</b>"));
        }


        builder.setCancelable(cancelable);

        AlertDialog dialog = builder.create();

        dialog.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);


        return dialog;
    }


    public static AlertDialog ShowFinishTestDialog(Activity activity, String title, String message, String positiveButtonTitle, DialogInterface.OnClickListener positiveButtonListener, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(message);
        }

        if (positiveButtonTitle != null) {
            builder.setPositiveButton(positiveButtonTitle, positiveButtonListener == null ? new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            } : positiveButtonListener);
        }

        builder.setCancelable(cancelable);

        AlertDialog dialog = builder.create();

        dialog.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);


        return dialog;
    }



}
