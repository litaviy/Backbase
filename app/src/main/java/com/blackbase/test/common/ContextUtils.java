package com.blackbase.test.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by klitaviy on 1/25/19-1:01 PM.
 */
public class ContextUtils {
    public static void hideKeyboardFrom(Context context, View view) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
