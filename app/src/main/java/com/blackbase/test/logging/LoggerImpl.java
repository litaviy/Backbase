package com.blackbase.test.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by klitaviy on 1/23/19-10:08 PM.
 */
public class LoggerImpl implements Logger {
    @Override
    public void exception(@NonNull Exception exception) {
        exception.printStackTrace();
    }

    @Override
    public void message(@Nullable final String message) {

    }
}
