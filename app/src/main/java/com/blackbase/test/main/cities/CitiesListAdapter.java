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
                    (TextView) convertView.findViewById(R.id.cityName),
                    (TextView) convertView.findViewById(R.id.cityCountry),
                    (TextView) convertView.findViewById(R.id.cityCoordinates)
            );

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CityModel cityModel = getItem(position);

        if (Condition.isNotNull(cityModel)) {
            viewHolder.mCityName.setText(cityModel.getName());
            viewHolder.mCityCountry.setText(cityModel.getCountry());

            final String coordinatesTitle = getContext().getString(
                    R.string.city_coordinates, cityModel.getCoordinates().getLatitude(), cityModel.getCoordinates().getLongitude()
            );
            viewHolder.mCityCoordinates.setText(coordinatesTitle);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mCityClickListener.onCityCoordinatesClick(cityModel.getCoordinates());
                }
            });
        }

        return convertView;
    }

    public void swapData(@NonNull final List<CityModel> cityModels) {
        clear();
        addAll(cityModels);
    }

    private static class ViewHolder {
        @NonNull
        final TextView mCityName;
        @NonNull
        final TextView mCityCountry;
        @NonNull
        final TextView mCityCoordinates;

        private ViewHolder(@NonNull final TextView cityName,
                           @NonNull final TextView cityCountry,
                           @NonNull final TextView cityCoordinates) {
            mCityName = cityName;
            mCityCountry = cityCountry;
            mCityCoordinates = cityCoordinates;
        }
    }
}
