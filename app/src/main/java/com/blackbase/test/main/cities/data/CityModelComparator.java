package com.blackbase.test.main.cities.data;

import java.util.Comparator;

/**
 * Created by klitaviy on 1/25/19-3:12 PM.
 */
public class CityModelComparator implements Comparator<CityModel> {
    @Override
    public int compare(final CityModel city1, final CityModel city2) {
        final int result = city1.getName().compareTo(city2.getName());
        if (result != 0) {
            return result;
        } else {
            return city1.getCountry().compareTo(city2.getCountry());
        }
    }
}
