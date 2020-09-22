/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
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
