package com.trainrest.utils;

import com.trainrest.model.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Класс-утилита для преобразования json объектов в сущности
 */
public class Filler {
    /**
     * Инициализатор класса
     */
    public Filler(){}
    /**
     * Преобразует JSONObject в сущность поезда
     * @param rawTrain JSONObject
     * @return train
     */
    public static Train fillTrain(JSONObject rawTrain){
        Train train = new Train();
        train.setId(rawTrain.getLong("id"));
        train.setName(rawTrain.getString("name"));
        train.setType(rawTrain.getString("type"));
        train.setNumberOfCars(rawTrain.getInt("numberOfCars"));
        train.setNumberOfSeatsPerCar(rawTrain.getInt("numberOfSeatsPerCar"));
        return train;
    }

    /**
     * Преобразует JSONObject в сущность рейса
     * @param rawRoute JSONObject
     * @return PositionEntity
     */
    public static Route fillRoute(JSONObject rawRoute){
        Route route = new Route();
        route.setId(rawRoute.getLong("id"));
        route.setDepartureCity(rawRoute.getString("departureCity"));
        route.setArrivalCity(rawRoute.getString("arrivalCity"));
        route.setDepartureDate(LocalDate.parse(rawRoute.getString("departureDate")));
        route.setDepartureTime(LocalTime.parse(rawRoute.getString("departureTime")));
        route.setArrivalDate(LocalDate.parse(rawRoute.getString("arrivalDate")));
        route.setArrivalTime(LocalTime.parse(rawRoute.getString("arrivalTime")));
        route.setBasePrice(rawRoute.getLong("basePrice"));
        return route;
    }

}
