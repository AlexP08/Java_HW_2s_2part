package com.TrainFX.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * Сущность для маршрута
 * @author Alexander Pavlov
 */

public class Route {
    private Long id;
    private String routeNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private Train train;

    public Route() {}

    public Route(String routeNumber, String departureCity, String arrivalCity, LocalDate departureDate, LocalDate departureTime, LocalDate arrivalTime, Train train) {
        this.routeNumber = routeNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.train = train;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }


    /**
     * Возвращает SimpleStringProperty для параметра номера рейса
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getRouteNumberProperty(){
        return new SimpleStringProperty(routeNumber);
    }

    /**
     * Возвращает SimpleStringProperty для параметра города-началльного пункта
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getDepartureCityProperty(){
        return new SimpleStringProperty(departureCity);
    }

    /**
     * Возвращает SimpleStringProperty для параметра города-конечного пункта
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getArrivalCityProperty(){
        return new SimpleStringProperty(arrivalCity);
    }
}
