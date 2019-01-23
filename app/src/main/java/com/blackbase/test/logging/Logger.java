package com.blackbase.test.logging;

import android.support.annotation.NonNull;

/**
 * Created by klitaviy on 1/23/19-9:48 PM.
 */
public interface Logger {
    void exception(@NonNull final Exception exception);
}
