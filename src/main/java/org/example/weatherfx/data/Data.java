package org.example.weatherfx.data;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.*;
import java.util.Scanner;

public class Data implements DomainInterface {
    private String apiKey = "";

    public Data() {
        loadApiKey();
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void loadApiKey() {
        try (Scanner scanner = new Scanner(new File("src/main/java/org/example/weatherfx/secret.txt"))) {
            String apiKey = scanner.nextLine();
            setApiKey(apiKey);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void storeApiKey(String apiKey) {
        try (Writer writer = new FileWriter("src/main/java/org/example/weatherfx/secret.txt")) {
            writer.write(apiKey);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCityData(String city) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city.replace(" ", "_") + "&aqi=no");

            return client.execute(request, httpResponse -> {
                Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
                StringBuilder builder = new StringBuilder();

                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                System.out.println(builder.toString());
                return builder.toString();
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
