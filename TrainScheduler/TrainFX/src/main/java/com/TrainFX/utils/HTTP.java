package com.TrainFX.utils;


import com.TrainFX.exception.NoConnectionException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Класс для REST запросов
 * @author Alexander Pavlov
 */
public class HTTP {
    /**
     * отправляет GET-запроса и возвращает ответ
     * @param urlString String
     * @return String
     * @throws NoConnectionException если при соединении возникли ошибки
     */
    public static String GetRequest(String urlString) throws NoConnectionException {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            return getString(conn);
        } catch (IOException e) {
            throw new NoConnectionException("No connection");
        }
    }

    private static String getString(URLConnection conn) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        return sb.toString();
    }

    /**
     * POST-запроса
     * @param link String
     * @param jsonObject JSONObject
     * @return String
     * @throws NoConnectionException обработка ошибок
     * @throws IOException обработка ошибок
     */
    public static String Post(String link, JSONObject jsonObject)
            throws NoConnectionException, IOException {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (ConnectException e) {
            throw new NoConnectionException("No connection");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * PUT-запроса
     * @param urlString String
     * @param jsonString JSONObject
     * @return String
     * @throws NoConnectionException обработка ошибок
     */
    public static String PutRequest(String urlString, String jsonString) throws NoConnectionException{
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("PUT");
            http.setDoOutput(true);

            byte[] out = jsonString.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            return getString(conn);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NoConnectionException("No connection");
        }
    }

    /**
     * DELETE-запроса
     * @param urlString String
     * @return boolean
     * @throws NoConnectionException обработка ошибок
     */
    public static boolean DeleteRequest(String urlString) throws  NoConnectionException{
        try {

            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("DELETE");
            http.setDoOutput(true);
            http.connect();

            http.getResponseCode();
            return true;
        } catch (IOException e) {
            throw new NoConnectionException("No connection");
        }
    }
}

