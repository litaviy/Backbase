package com.blackbase.test.main.data.filtering;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by klitaviy on 1/23/19-11:05 PM.
 */
public interface FilteredResultsListener<T> {
    void onFilteredResultsDelivered(@NonNull final List<T> filteredResults);
}
