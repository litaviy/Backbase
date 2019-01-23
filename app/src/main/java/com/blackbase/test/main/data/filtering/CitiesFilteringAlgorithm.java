package com.blackbase.test.main.data.filtering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.main.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/23/19-10:58 PM.
 */
public interface CitiesFilteringAlgorithm {
    @NonNull
    List<CityModel> invoke(@Nullable final CharSequence constraint);
}
