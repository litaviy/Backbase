package com.blackbase.test.main.cities.data.worker;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.common.Destroyable;

/**
 * Created by klitaviy on 1/24/19-4:59 PM.
 */
public class Worker<T> extends AsyncTask<Void, Void, T> implements Destroyable {

    @Nullable
    private WorkerListener<T> mResultProvider;

    public Worker(@Nullable final WorkerListener<T> resultProvider) {
        mResultProvider = resultProvider;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (canDoAction()) {
            mResultProvider.onInitialSetup();
        }
    }

    @Override
    protected T doInBackground(final Void... voids) {
        if (canDoAction()) {
            final T result = mResultProvider.provideResults();
            return result;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(final T t) {
        super.onPostExecute(t);
        if (canDoAction()) {
            mResultProvider.onResultsProvided(t);
        }
    }

    @Override
    public void destroy() {
        cancel(true);
        mResultProvider = null;
    }

    private boolean canDoAction() {
        return Condition.isNotNull(mResultProvider) && !isCancelled();
    }
}
