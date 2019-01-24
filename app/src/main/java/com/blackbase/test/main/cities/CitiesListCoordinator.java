package com.blackbase.test.main.cities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.Coordinates;

/**
 * Created by klitaviy on 1/24/19-6:07 PM.
 */
final class CitiesListCoordinator implements CitiesListContract.Coordinator {

    @Nullable
    private Activity mActivity;

    CitiesListCoordinator(@Nullable final Activity activity) {
        mActivity = activity;
    }

    @Override
    public void destroy() {
        mActivity = null;
    }

    @Override
    public void cityCoordinatesClicked(@NonNull final Coordinates coordinates) {
        if (Condition.isNotNull(mActivity)) {
            // TODO - show map
        }
    }
}
