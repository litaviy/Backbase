package com.blackbase.test.main.cities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.blackbase.test.R;
import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CityModel;
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
    public void cityCoordinatesClicked(@NonNull final CityModel cityModel) {
        if (Condition.isNotNull(mActivity)) {
            /*
            Intention is not to google maps directly, because user must have a choice.
             */
            final Coordinates coordinates = cityModel.getCoordinates();
            final String latitude = String.valueOf(coordinates.getLatitude()).replace(',', '.');
            final String longitude = String.valueOf(coordinates.getLongitude()).replace(',', '.');
            final Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(mActivity.getString(R.string.maps_intent_action_geo, latitude, longitude))
            );
            if (Condition.isNotNull(intent.resolveActivity(mActivity.getPackageManager()))) {
                mActivity.startActivity(intent);
            } else {
                Toast.makeText(mActivity, R.string.cannot_handle_intent_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
