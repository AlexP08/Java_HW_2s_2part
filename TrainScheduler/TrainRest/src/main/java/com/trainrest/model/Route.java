package com.trainrest.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Сущность для Рейса
 * @author Alexander Pavlov
 */
@Entity
@Table(name = "route")
public class Route {
    /**
     * Идентификатор рейса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Город отправления
     */
    @Column(name = "departure_city")
    private String departureCity;

    /**
     * Город прибытия
     */
    @Column(name = "arrival_city")
    private String arrivalCity;

    /**
     * Дата отправления
     */
    @Column(name = "departure_date")
    private LocalDate departureDate;

    /**
     * Время отправления
     */
    @Column(name = "departure_time")
    private LocalTime departureTime;

    /**
     * Дата прибытия
     */
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    /**
     * Время прибытия
     */
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    /**
     * Базовая цена
     */
    @Column(name = "base_price")
    private Long basePrice;

    /**
     * Поезд, на котором осуществляется рейс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    private Train train;

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}