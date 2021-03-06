package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/23/19-10:58 PM.
 */
public interface CitiesFilteringAlgorithm {
    @NonNull
    List<CityModel> invoke(@Nullable final CharSequence constraint,
                           @NonNull final TreeMap<String, TreeSet<CityModel>> target);
}
