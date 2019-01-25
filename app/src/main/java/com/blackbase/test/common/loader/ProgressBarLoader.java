package com.blackbase.test.common.loader;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.blackbase.test.R;
import com.blackbase.test.common.Condition;

import java.lang.ref.WeakReference;

/**
 * Created by klitaviy on 1/24/19-10:49 AM.
 */
final class ProgressBarLoader implements Loader {

    @NonNull
    private final WeakReference<AlertDialog> mDialog;

    ProgressBarLoader(@NonNull final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.progress_dialog);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        mDialog = new WeakReference<>(alertDialog);
    }

    @Override
    public void show() {
        if (Condition.isNotNull(mDialog.get())) {
            mDialog.get().show();
        }
    }

    @Override
    public void hide() {
        if (Condition.isNotNull(mDialog.get())) {
            mDialog.get().dismiss();
        }
    }
}
