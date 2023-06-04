package com.example.bookerfx.http;

import com.example.bookerfx.model.Booking;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestHandler {
    public final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final Gson gson;

    public HttpRequestHandler() {
        gson = createGsonInstance();
    }

    public void addBooking(Booking booking) {
        try {
            URL url = new URL("http://localhost:8080/bookings/");
            HttpURLConnection connection = openConnection(url, "POST");
            String jsonBooking = gson.toJson(booking);
            sendJsonRequest(connection, jsonBooking);
            handleResponse(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        try {
            URL url = new URL("http://localhost:8080/bookings");
            HttpURLConnection connection = openConnection(url, "GET");
            String jsonResponse = getJsonResponse(connection);
            return gson.fromJson(jsonResponse, new TypeToken<List<Booking>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Booking> getBookingsByRoomId(int roomId) {
        try {
            URL url = new URL("http://localhost:8080/bookings/room-id/" + roomId);
            HttpURLConnection connection = openConnection(url, "GET");
            String jsonResponse = getJsonResponse(connection);
            return gson.fromJson(jsonResponse, new TypeToken<List<Booking>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private HttpURLConnection openConnection(URL url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    private void sendJsonRequest(HttpURLConnection connection, String json) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private String getJsonResponse(HttpURLConnection connection) throws IOException {
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            return responseBuilder.toString();
        } else {
            System.out.println("Error: " + connection.getResponseCode());
        }
        return "";
    }

    private void handleResponse(HttpURLConnection connection) {
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Successfully booked");
            } else {
                System.out.println("Error: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Gson createGsonInstance() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(date.format(localDateTimeFormatter));
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(date.format(localDateFormatter));
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), localDateTimeFormatter);
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), localDateFormatter);
                    }
                })
                .create();
    }
}
