package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        Properties props = new Properties();
        String apiKey;

        // === 1. Load API key ===
        try (InputStream input = App.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("❌ config.properties file not found. Please place your API key in config.properties");
                return;
            }
            props.load(input);
            apiKey = props.getProperty("api.key");
            if (apiKey == null || apiKey.isEmpty()) {
                System.out.println("❌ API key not specified. Please put it in config.properties");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading config.properties");
            return;
        }

        // === 2. One shared Scanner (not closed) ===
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌤 Welcome to the Weather Client!");
        System.out.println("Enter a command (type 'help' for assistance):");

        // === 3. Main program loop ===
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("👋 Exiting the program...");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                printHelp();
            } else if (input.toLowerCase().startsWith("weather")) {
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) {
                    System.out.println("⚠ Please specify a city. Example: weather London");
                } else {
                    String city = parts[1];
                    getWeather(city, apiKey);
                }
            } else {
                System.out.println("❓ Unknown command. Type 'help' for available commands.");
            }
        }

        // We do not close System.in to avoid "Scanner closed" error
        // scanner.close(); // ❌ REMOVED
    }

    // === 4. Help section ===
    private static void printHelp() {
        System.out.println("""
                Available commands:
                weather <city> — get weather by city name
                help — show this help message
                quit — exit the program
                Example: weather New York
                """);
    }

    // === 5. Weather request ===
    private static void getWeather(String city, String apiKey) {
        try {
            String url = API_URL + "?q=" + city + "&appid=" + apiKey + "&units=metric&lang=en";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("❌ City not found or request failed. Code: " + response.statusCode());
                return;
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String description = json.getAsJsonArray("weather").get(0)
                    .getAsJsonObject().get("description").getAsString();
            double temp = json.getAsJsonObject("main").get("temp").getAsDouble();
            int humidity = json.getAsJsonObject("main").get("humidity").getAsInt();
            double wind = json.getAsJsonObject("wind").get("speed").getAsDouble();

            System.out.printf("""
                    🌍 Current weather in %s:
                    Temperature: %.1f°C
                    Description: %s
                    Humidity: %d%%
                    Wind speed: %.1f m/s
                    """, city, temp, description, humidity, wind);

        } catch (IOException | InterruptedException e) {
            System.out.println("⚠ Error while contacting weather service: " + e.getMessage());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        } catch (com.google.gson.JsonSyntaxException e) {
            System.out.println("⚠ Error parsing JSON response: " + e.getMessage());
        }
    }
}
