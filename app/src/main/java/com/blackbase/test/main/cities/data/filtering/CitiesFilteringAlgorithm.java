package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/23/19-10:58 PM.
 */
public interface CitiesFilteringAlgorithm {
    @NonNull
    List<CityModel> invoke(@Nullable final CharSequence constraint,
                           @NonNull final List<CityModel> target);
}
