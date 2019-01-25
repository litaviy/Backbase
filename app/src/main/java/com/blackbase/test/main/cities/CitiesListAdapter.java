package com.blackbase.test.main.cities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blackbase.test.R;
import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CityModel;
import com.blackbase.test.main.cities.data.Coordinates;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-7:13 PM.
 */
class CitiesListAdapter extends ArrayAdapter<CityModel> {

    @NonNull
    private final CitiesListContract.View.CityClickListener mCityClickListener;

    CitiesListAdapter(@NonNull final Context context,
                      @NonNull final List<CityModel> cities,
                      @NonNull final CitiesListContract.View.CityClickListener cityClickListener) {
        super(context, R.layout.city_item, cities);
        mCityClickListener = cityClickListener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.city_item, parent, false);

            viewHolder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.cityTitle),
                    (TextView) convertView.findViewById(R.id.cityCoordinates)
            );

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CityModel cityModel = getItem(position);

        if (Condition.isNotNull(cityModel)) {
            final String cityTitle = getContext().getString(
                    R.string.city_title, cityModel.getName(), cityModel.getCountry()
            );
            viewHolder.mCityTitle.setText(cityTitle);

            final Coordinates coordinates = cityModel.getCoordinates();
            final String coordinatesTitle = getContext().getString(
                    R.string.city_coordinates, coordinates.getLatitude(), coordinates.getLongitude()
            );
            viewHolder.mCityCoordinates.setText(coordinatesTitle);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mCityClickListener.onCityCoordinatesClick(cityModel);
                }
            });
        }

        return convertView;
    }

    void swapData(@NonNull final List<CityModel> cityModels) {
        clear();
        addAll(cityModels);
    }

    private static class ViewHolder {
        @NonNull
        final TextView mCityTitle;
        @NonNull
        final TextView mCityCoordinates;

        private ViewHolder(@NonNull final TextView cityTitle,
                           @NonNull final TextView cityCoordinates) {
            mCityTitle = cityTitle;
            mCityCoordinates = cityCoordinates;
        }
    }
}
