package org.example.weatherfx.data;

public interface DomainInterface {
    String getCityData(String city);
    void storeApiKey(String apiKey);
    void loadApiKey();
}
