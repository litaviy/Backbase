package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CityModel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/24/19-6:20 PM.
 */
public class CitiesFilteringAlgorithmImpl implements CitiesFilteringAlgorithm {

    @NonNull
    private List<CityModel> mEmptyResult = new LinkedList<>();

    /**
     * Uses first letter of constraint value as a key for the map.
     * The map provides List<CityModel> which contains cities which starts with first letter of constraint value,
     * so we don't look on another names.
     * After is used linear search in given List<CityModel>.
     *
     * @param constraint - filtering query.
     * @param target     - data source for filtering.
     * @return - filtered result or all the data from target if there is no any results.
     */
    @NonNull
    @Override
    public List<CityModel> invoke(@Nullable final CharSequence constraint, @NonNull final TreeMap<String, TreeSet<CityModel>> target) {
        if (Condition.isNull(constraint) || constraint.length() == 0) {
            if (mEmptyResult.isEmpty()) {
                for (TreeSet<CityModel> cityModelTreeSet : target.values()) {
                    mEmptyResult.addAll(cityModelTreeSet);
                }
            }
            return mEmptyResult;
        } else {
            final String filter = String.valueOf(constraint);
            final String key = filter.substring(0, 1);
            final TreeSet<CityModel> firstLevelCitiesLowerCase = target.get(key.toLowerCase());
            final TreeSet<CityModel> firstLevelCitiesUpperCase = target.get(key.toUpperCase());

            final List<CityModel> results = new LinkedList<>();
            if (constraint.length() == 1) {
                if (Condition.isNotNull(firstLevelCitiesLowerCase)) {
                    results.addAll(firstLevelCitiesLowerCase);
                }
                if (Condition.isNotNull(firstLevelCitiesUpperCase)) {
                    results.addAll(firstLevelCitiesUpperCase);
                }
            } else {
                if (Condition.isNotNull(firstLevelCitiesLowerCase)) {
                    results.addAll(filterCities(filter, firstLevelCitiesLowerCase));
                }
                if (Condition.isNotNull(firstLevelCitiesUpperCase)) {
                    results.addAll(filterCities(filter, firstLevelCitiesUpperCase));
                }
            }

            return results;
        }
    }

    private List<CityModel> filterCities(@NonNull final String filter, @NonNull final Collection<CityModel> target) {
        final List<CityModel> results = new LinkedList<>();
        for (CityModel cityModel : target) {
            if (cityModel.getName().toLowerCase().startsWith(filter.toLowerCase())) {
                results.add(cityModel);
            }
        }
        return results;
    }
}
