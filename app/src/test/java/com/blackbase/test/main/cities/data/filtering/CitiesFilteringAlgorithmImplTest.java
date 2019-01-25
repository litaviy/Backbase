package com.blackbase.test.main.cities.data.filtering;

import com.blackbase.test.main.cities.data.CityModel;
import com.blackbase.test.main.cities.data.CityModelComparator;
import com.blackbase.test.main.cities.data.Coordinates;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by klitaviy on 1/25/19-3:06 PM.
 */
public class CitiesFilteringAlgorithmImplTest {

    private final CitiesFilteringAlgorithm mCitiesFilteringAlgorithm = new CitiesFilteringAlgorithmImpl();
    private final TreeMap<String, TreeSet<CityModel>> mCitiesMap = new TreeMap<>();

    @Before
    public void setUp() throws Exception {
        final CityModelComparator cityModelComparator = new CityModelComparator();
        final TreeSet<CityModel> aCities = new TreeSet<>(cityModelComparator);
        aCities.add(new CityModel("", "Alabama", 1, new Coordinates(0.0f, 0.0f)));
        aCities.add(new CityModel("", "Alibama", 1, new Coordinates(0.0f, 0.0f)));
        aCities.add(new CityModel("", "Ahobama", 1, new Coordinates(0.0f, 0.0f)));
        mCitiesMap.put("A", aCities);

        final TreeSet<CityModel> bCities = new TreeSet<>(cityModelComparator);
        bCities.add(new CityModel("", "Babama", 1, new Coordinates(0.0f, 0.0f)));
        mCitiesMap.put("B", bCities);
    }

    @Test
    public void invoke_find_A_cities() {
        final List<CityModel> cityModels = mCitiesFilteringAlgorithm.invoke("A", mCitiesMap);
        assertEquals(3, cityModels.size());
    }

    @Test
    public void invoke_find_Al_cities() {
        final List<CityModel> cityModels = mCitiesFilteringAlgorithm.invoke("Al", mCitiesMap);
        assertEquals(2, cityModels.size());
    }

    @Test
    public void invoke_invalid() {
        // Invoke returns all the cities in the map.
        final List<CityModel> cityModels = mCitiesFilteringAlgorithm.invoke("", mCitiesMap);
        assertEquals(4, cityModels.size());
    }
}