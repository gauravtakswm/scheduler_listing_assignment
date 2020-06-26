package com.gauravtak.scheduler_assignment.utils_classes;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.gauravtak.scheduler_assignment.R;


public class CustomProgressDialog {

    private static Dialog dialog;

    public static void showProgress(Context mcontext) {
        try {
            if (dialog == null) {
                dialog = new Dialog(mcontext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.dialog_progressbar);
            }
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideprogressbar() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
