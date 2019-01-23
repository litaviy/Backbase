package com.blackbase.test.common;

import android.support.annotation.Nullable;

/**
 * Created by klitaviy on 1/23/19-9:54 PM.
 */
public class Condition {
    public static boolean isNull(@Nullable final Object o) {
        return o == null;
    }

    public static boolean isNotNull(@Nullable final Object o) {
        return o != null;
    }
}
