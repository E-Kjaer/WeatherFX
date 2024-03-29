package org.example.weatherfx.domain;

public interface JavaFXPresentation {
    public boolean addCity(String city);
    public String getCityData(String city);
    public String getCityData(int index);
    public String getCityName(int index);
    public int getListSize();
    public void updateCity(int index);
    public boolean isNull(int index);
    public void storeApiKey(String apiKey);
    public void loadApiKey();
}
