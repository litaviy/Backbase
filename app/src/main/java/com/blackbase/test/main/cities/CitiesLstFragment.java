package com.blackbase.test.main.cities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blackbase.test.R;
import com.blackbase.test.common.BaseFragment;
import com.blackbase.test.common.Condition;
import com.blackbase.test.common.ContextUtils;
import com.blackbase.test.common.TextWatcherAdapter;
import com.blackbase.test.common.loader.Loader;
import com.blackbase.test.main.cities.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-10:29 AM.
 */
public final class CitiesLstFragment extends BaseFragment implements CitiesListContract.View {

    public static final String TAG = "cities_fragment";
    private static final String LIST_INSTANCE_STATE = "LIST_INSTANCE_STATE";

    @Nullable
    private CitiesListContract.Presenter mPresenter;

    private EditText mCitiesFilterField;
    private ListView mCitiesList;
    @Nullable
    private Parcelable mCitiesListState;
    @Nullable
    private CitiesListAdapter mCitiesListAdapter;

    @Nullable
    private Loader mLoader;
    @NonNull
    private TextWatcherAdapter mFilteringWatcherAdapter = new TextWatcherAdapter() {
        @Override
        public void afterTextChanged(final Editable s) {
            super.afterTextChanged(s);
            if (Condition.isNotNull(mPresenter)) {
                mPresenter.onFilterFieldHasChanged(s);
            }
        }
    };

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        final Activity activity = getActivity();
        if (Condition.isNotNull(activity)) {
            mLoader = CitiesListDependenciesProvider.provideLoader(activity);
            mPresenter = CitiesListDependenciesProvider.providePresenter(activity, this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cities_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCitiesList = view.findViewById(R.id.citiesList);

        mCitiesFilterField = view.findViewById(R.id.citiesFilterField);
        mCitiesFilterField.addTextChangedListener(mFilteringWatcherAdapter);
        mCitiesFilterField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    final Activity activity = getActivity();
                    if (Condition.isNotNull(activity)) {
                        ContextUtils.hideKeyboardFrom(activity, view);
                    }
                    mCitiesList.requestFocus();
                    return true;
                } else {
                    return false;
                }
            }
        });

        if (Condition.isNotNull(savedInstanceState)) {
            mCitiesListState = savedInstanceState.getParcelable(LIST_INSTANCE_STATE);
        }

        if (Condition.isNotNull(mPresenter)) {
            mPresenter.onViewReady();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LIST_INSTANCE_STATE, mCitiesList.onSaveInstanceState());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCitiesFilterField.removeTextChangedListener(mFilteringWatcherAdapter);
        if (Condition.isNotNull(mPresenter)) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }

    @Override
    public void showProgress() {
        if (Condition.isNotNull(mLoader)) {
            mLoader.show();
        }
    }

    @Override
    public void hideProgress() {
        if (Condition.isNotNull(mLoader)) {
            mLoader.hide();
        }
    }

    @Override
    public void setCities(@NonNull final List<CityModel> cities, @NonNull final CityClickListener cityClickListener) {
        final Activity activity = getActivity();
        if (Condition.isNotNull(activity)) {
            mCitiesListAdapter = new CitiesListAdapter(activity, cities, cityClickListener);
            mCitiesList.setAdapter(mCitiesListAdapter);
            if (Condition.isNotNull(mCitiesListState)) {
                mCitiesList.onRestoreInstanceState(mCitiesListState);
            }
        }
    }

    @Override
    public void updateCities(@NonNull final List<CityModel> cities) {
        if (Condition.isNotNull(mCitiesListAdapter)) {
            mCitiesListAdapter.swapData(cities);
        }
    }
}
