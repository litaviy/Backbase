package com.blackbase.test.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.blackbase.test.R;
import com.blackbase.test.common.BaseFragment;
import com.blackbase.test.common.Condition;
import com.blackbase.test.common.TextWatcherAdapter;
import com.blackbase.test.common.loader.Loader;
import com.blackbase.test.main.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-10:29 AM.
 */
public final class CitiesLstFragment extends BaseFragment implements CitiesListContract.View {

    public static final String TAG = "cities_fragment";

    @Nullable
    private CitiesListContract.Presenter mPresenter;

    private EditText mCitiesFilterField;
    private ListView mCitiesList;

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
        mLoader = CitiesListDependenciesProvider.provideLoader();

        final Activity activity = getActivity();
        if (Condition.isNotNull(activity)) {
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

        mCitiesFilterField = view.findViewById(R.id.citiesFilterField);
        mCitiesFilterField.addTextChangedListener(mFilteringWatcherAdapter);

        mCitiesList = view.findViewById(R.id.citiesList);

        if (Condition.isNotNull(mPresenter)) {
            mPresenter.onViewReady();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        // TODO
    }

    @Override
    public void updateCities(@NonNull final List<CityModel> cities) {
        // TODO
    }
}
