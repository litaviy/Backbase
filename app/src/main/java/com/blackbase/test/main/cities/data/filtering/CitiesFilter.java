package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;
import android.widget.Filter;

import com.blackbase.test.common.Destroyable;
import com.blackbase.test.main.cities.data.CityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/23/19-10:45 PM.
 */
public class CitiesFilter extends Filter implements Destroyable {

    @NonNull
    private final CitiesFilteringAlgorithm mCitiesFilteringAlgorithm;
    @NonNull
    private final TreeMap<String, TreeSet<CityModel>> mSource;
    @NonNull
    private final ArrayList<FilteredResultsListener<CityModel>> mFilterResultsListeners;

    CitiesFilter(@NonNull final CitiesFilteringAlgorithm citiesFilteringAlgorithm,
                 @NonNull final TreeMap<String, TreeSet<CityModel>> source) {
        mCitiesFilteringAlgorithm = citiesFilteringAlgorithm;
        mSource = source;
        mFilterResultsListeners = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(final CharSequence constraint) {
        final List<CityModel> algorithmResults = mCitiesFilteringAlgorithm.invoke(constraint, mSource);

        final FilterResults filterResults = new FilterResults();
        filterResults.values = algorithmResults;
        filterResults.count = algorithmResults.size();

        return filterResults;
    }

    @Override
    protected void publishResults(final CharSequence constraint, final FilterResults results) {
        try {
            final List<CityModel> typedFilteringResults = (List<CityModel>) results.values;

            for (FilteredResultsListener<CityModel> listener : mFilterResultsListeners) {
                listener.onFilteredResultsDelivered(typedFilteringResults);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void registerListener(@NonNull final FilteredResultsListener<CityModel> listener) {
        mFilterResultsListeners.add(listener);
    }

    public void unregisterListener(@NonNull final FilteredResultsListener<CityModel> listener) {
        mFilterResultsListeners.remove(listener);
    }

    @Override
    public void destroy() {
        mFilterResultsListeners.clear();
    }
}
