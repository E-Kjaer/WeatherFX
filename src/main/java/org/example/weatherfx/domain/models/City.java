package org.example.weatherfx.domain.models;

public class City {
    private String name;
    private float temperature;
    private boolean day;
    private float windSpeed;
    private float pressureMb;
    private int cloud;
    private float feelsLike;
    private float visibility;
    private float uv;

    public City(String name, float temperature, boolean day, float windSpeed, float pressureMb, int cloud, float feelsLike, float visibility, float uv) {
        this.name = name;
        this.temperature = temperature;
        this.day = day;
        this.windSpeed = windSpeed;
        this.pressureMb = pressureMb;
        this.cloud = cloud;
        this.feelsLike = feelsLike;
        this.visibility = visibility;
        this.uv = uv;
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isDay() {
        return day;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getPressureMb() {
        return pressureMb;
    }

    public int getCloud() {
        return cloud;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getUv() {
        return uv;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof String) {
            return this.name.equals((String) o);
        } else if (o instanceof City) {
            return this.name.equals(((City) o).getName());
        }
        else {
            return false;
        }
    }

    public String getCityData() {
        return "Temperature: " + this.temperature + " ℃\n" +
                "IsDay: " + this.day + "\n" +
                "Windspeed: " + this.windSpeed + " km/t\n" +
                "Pressure: " + this.pressureMb + " mb\n" +
                "Cloud: " + this.cloud + "\n" +
                "Feels like: " + this.feelsLike + " ℃\n" +
                "Visibility: " + this.visibility + " km\n" +
                "UV: " + this.uv;
    }

    @Override
    public String toString() {
        return "";
    }
}
