package com.TrainFX.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Сущность для поезда
 * @author Alexander Pavlov
 */

public class Train {
    private Long id;
    private String trainNumber;
    private String trainType;
    private String trainRoute;

    public Train() {}

    public Train(String trainNumber, String trainType, String trainRoute) {
        this.trainNumber = trainNumber;
        this.trainType = trainType;
        this.trainRoute = trainRoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(String trainRoute) {
        this.trainRoute = trainRoute;
    }
    /**
     * Возвращает SimpleStringProperty для параметра номера поезда
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getTrainNumberProperty(){
        return new SimpleStringProperty(trainNumber);
    }

    /**
     * Возвращает SimpleStringProperty для параметра типа поезда
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getTrainTypeProperty(){
        return new SimpleStringProperty(trainType);
    }

    /**
     * Возвращает SimpleStringProperty для параметра маршрута поезда
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getTrainRouteProperty(){
        return new SimpleStringProperty(trainRoute);
    }


}