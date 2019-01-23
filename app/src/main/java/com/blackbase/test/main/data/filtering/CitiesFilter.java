package com.blackbase.test.main.data.filtering;

import android.support.annotation.NonNull;
import android.widget.Filter;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.data.CityModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by klitaviy on 1/23/19-10:45 PM.
 */
public class CitiesFilter extends Filter {

    @NonNull
    private final CitiesFilteringAlgorithm mCitiesFilteringAlgorithm;
    @NonNull
    private final ArrayList<WeakReference<FilteredResultsListener<CityModel>>> mFilterResultsListeners;

    public CitiesFilter(@NonNull final CitiesFilteringAlgorithm citiesFilteringAlgorithm) {
        mCitiesFilteringAlgorithm = citiesFilteringAlgorithm;
        mFilterResultsListeners = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(final CharSequence constraint) {
        final List<CityModel> algorithmResults = mCitiesFilteringAlgorithm.invoke(constraint);

        final FilterResults filterResults = new FilterResults();
        filterResults.values = algorithmResults;
        filterResults.count = algorithmResults.size();

        return filterResults;
    }

    @Override
    protected void publishResults(final CharSequence constraint, final FilterResults results) {
        try {
            final List<CityModel> typedFilteringResults = (List<CityModel>) results.values;

            for (WeakReference<FilteredResultsListener<CityModel>> listener : mFilterResultsListeners) {
                if (Condition.isNotNull(listener.get())) {
                    listener.get().onFilteredResultsDelivered(typedFilteringResults);
                }
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void registerListener(@NonNull final WeakReference<FilteredResultsListener<CityModel>> listener) {
        mFilterResultsListeners.add(listener);
    }

    public void unregisterListener(@NonNull final WeakReference<FilteredResultsListener<CityModel>> listener) {
        mFilterResultsListeners.remove(listener);
    }
}
