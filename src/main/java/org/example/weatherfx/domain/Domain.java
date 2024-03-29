package org.example.weatherfx.domain;

import org.example.weatherfx.data.DomainInterface;
import org.example.weatherfx.domain.models.City;
import org.example.weatherfx.data.Data;
import org.json.*;

import java.util.ArrayList;

public class Domain implements JavaFXPresentation {
    private ArrayList<City> cities;
    private DomainInterface data;

    public Domain() {
        this(new ArrayList<City>());
    }

    public Domain(ArrayList<City> cities) {
        this.cities = cities;
        this.data = new Data();
    }

    public int getListSize() {
        return cities.size();
    }

    public String getCityData(String city) {
        return cities.get(cities.indexOf(city)).toString();
    }

    public String getCityName(int index) {
        if (index > cities.size() - 1 || cities.get(index) == null) return "";
        return cities.get(index).getName();
    }

    public String getCityData(int index) {
        if (index > cities.size() - 1 || cities.get(index) == null) return "";

        return cities.get(index).getCityData();
    }

    public void updateCity(int index) {
        if (index > cities.size() - 1 || cities.get(index) == null) return;
        cities.set(index, getUpdateCityData(cities.get(index).getName()));
    }

    public boolean isNull(int index) {
        return cities.get(index) == null;
    }

    public City getUpdateCityData(String city) {
        String cityData = data.getCityData(city);

        JSONObject jsonData = new JSONObject(cityData);

        JSONObject weatherData = null;
        JSONObject locationData = null;

        try {
            weatherData = jsonData.getJSONObject("current");
            locationData = jsonData.getJSONObject("location");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new City(locationData.getString("name"),
                weatherData.getFloat("temp_c"),
                weatherData.getInt("is_day") == 1,
                weatherData.getFloat("wind_kph"),
                weatherData.getFloat("pressure_mb"),
                weatherData.getInt("cloud"),
                weatherData.getFloat("feelslike_c"),
                weatherData.getFloat("vis_km"),
                weatherData.getFloat("uv")
        );
    }

    // Checks if the city is already in the list, if not, add the city and update the city
    public boolean addCity(String city) {
        City cityObj = new City(city);
        if (cities.contains(cityObj)) return false;

        cities.add(cityObj);
        int index = cities.indexOf(cityObj);
        City updatedCity = getUpdateCityData(cityObj.getName());
        if (updatedCity != null) {
            cities.set(index, getUpdateCityData(cityObj.getName()));
            return true;
        } else {
            return false;
        }
    }

    public void storeApiKey(String apiKey) {
        data.storeApiKey(apiKey);
    }

    @Override
    public void loadApiKey() {
        data.loadApiKey();
    }
}
