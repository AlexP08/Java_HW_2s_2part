package com.TrainFX.service;

import com.TrainFX.exception.NoConnectionException;
import com.TrainFX.utils.DateUtil;
import com.TrainFX.utils.HTTP;
import com.TrainFX.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class API {
    /**
     * @author Alexander Pavlov
     * Возвращает значение начала строки для формирования ссылки
     * @return String
     */
    public static String localHost() {
        return "http://localhost:8080/";
    }

    /**
     * Запрос на получение всех поездов
     * @return ObservableList из Train
     * @throws NoConnectionException обработка ошибки
     */
    public static ObservableList<Train> getAllTrains() throws NoConnectionException{
        String url = localHost()+"trains/AllTrains";
        String request = HTTP.GetRequest(url);
        JSONArray rawResult = new JSONArray(request);
        ObservableList<Train> result = FXCollections.observableArrayList();
        for(int i=0; i<rawResult.length(); i++){
            try {
                result.add(fillTrain(rawResult.getJSONObject(i)));
            }catch (org.json.JSONException e){
                System.out.println("");
            }
        }
        return result;
    }

    /**
     * Запрос на добавление поезда в БД
     * @param jsonObject JSONObject
     * @return boolean
     * @throws NoConnectionException обработка ошибки
     */
    public static boolean addTrain(JSONObject jsonObject) throws NoConnectionException {
        String url = localHost() + "trains/addTrain";
        try {
            String request = HTTP.Post(url, jsonObject);
            return !request.equals("");
        }catch (IOException e){
            return false;
        }
    }

    public static boolean updateTrain(JSONObject jsonObject) throws NoConnectionException {
        String url = localHost() + "trains/update";
        String request = HTTP.PutRequest(url, String.valueOf(jsonObject));
        return !request.equals("");
    }

    public static void deleteTrain(Long id) throws NoConnectionException{
        String url = localHost() + "trains/id="+id;
        boolean request = HTTP.DeleteRequest(url);
    }

    /**
     * Запрос на получение рейсов
     * @return ObservableList из Route
     * @throws NoConnectionException обработка ошибки
     */
    public static ObservableList<Route> getAllRoutes() throws NoConnectionException{
        String url = localHost()+"routes/AllRoutes";
        String request = HTTP.GetRequest(url);
        JSONArray rawResult = new JSONArray(request);
        ObservableList<Route> result = FXCollections.observableArrayList();
        for(int i=0; i<rawResult.length(); i++){
            try {
                result.add(fillRoutes(rawResult.getJSONObject(i)));
            }catch (org.json.JSONException e){
                System.out.println("");
            }
        }
        return result;
    }

    /**
     * Запрос на добавление рейса в бд
     * @param jsonObject JSONObject
     * @return boolean
     * @throws NoConnectionException обработка ошибки
     */
    public static boolean addRoute(JSONObject jsonObject) throws NoConnectionException {
        String url = localHost() + "routes/addRoute";
        try {
            String request = HTTP.Post(url, jsonObject);
            return !request.equals("");
        }catch (IOException e){
            return false;
        }
    }

    public static boolean updateRoute(JSONObject jsonObject) throws NoConnectionException {
        String url = localHost() + "routes/update";
        String request = HTTP.PutRequest(url, String.valueOf(jsonObject));
        return !request.equals("");
    }

    public static void deleteRoute(Long id) throws NoConnectionException{
        String url = localHost() + "routes/id="+id;
        boolean request = HTTP.DeleteRequest(url);
    }

    public static Train fillTrain(JSONObject rawTrain){
        Train trainEntity = new Train();
        trainEntity.setId(rawTrain.getLong("id"));
        trainEntity.setTrainNumber(rawTrain.getString("trainNumber"));
        trainEntity.setTrainType(rawTrain.getString("trainType"));
        trainEntity.setTrainRoute(rawTrain.getString("trainRoute"));
        return trainEntity;
    }

    public static Route fillRoutes(JSONObject rawRoute){
        Route routeEntity = new Route();
        routeEntity.setId(rawRoute.getLong("id"));
        routeEntity.setRouteNumber(rawRoute.getString("routeNumber"));
        routeEntity.setDepartureCity(rawRoute.getString("departureCity"));
        routeEntity.setArrivalCity(rawRoute.getString("arrivalCity"));
        routeEntity.setDepartureDate(DateUtil.parse(rawRoute.getString("departureDate")));
        routeEntity.setDepartureTime(DateUtil.parse(rawRoute.getString("departureTime")));
        routeEntity.setArrivalTime(DateUtil.parse(rawRoute.getString("arrivalTime")));
        routeEntity.setTrain(fillTrain(rawRoute.getJSONObject("train")));
        return routeEntity;
    }

}
