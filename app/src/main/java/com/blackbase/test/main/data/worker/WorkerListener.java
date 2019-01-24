package com.blackbase.test.main.data.worker;

import android.support.annotation.Nullable;

/**
 * Created by klitaviy on 1/24/19-5:17 PM.
 */
public interface WorkerListener<T> {
    void onInitialSetup();

    T provideResults();

    void onResultsProvided(@Nullable final T results);
}
