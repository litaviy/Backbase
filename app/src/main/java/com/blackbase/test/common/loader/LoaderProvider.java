package com.blackbase.test.common.loader;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by klitaviy on 1/24/19-10:48 AM.
 */
public class LoaderProvider {
    @NonNull
    public static Loader getLoader(@NonNull final Activity activity) {
        /*
        Basing on whatever we want we provide loader presentation.
         */
        return new ProgressBarLoader(activity);
    }
}
