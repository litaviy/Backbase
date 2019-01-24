package com.blackbase.test.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by klitaviy on 1/23/19-9:48 PM.
 */
public interface Logger {
    void exception(@NonNull final Exception exception);

    void message(@Nullable final String message);
}
