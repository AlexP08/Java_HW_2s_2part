package com.trainrest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/**
 * Сущность для Поезда
 * @author Alexander Pavlov
 */
@Entity
@Table(name = "train")
public class Train {
    /**
     * Идентификатор поезда
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование поезда
     */
    @Column(name = "name")
    private String name;

    /**
     * Тип поезда
     */
    @Column(name = "type")
    private String type;

    /**
     * Количество вагонов
     */
    @Column(name = "number_of_cars")
    private Integer numberOfCars;

    /**
     * Количество мест в вагоне
     */
    @Column(name = "number_of_seats_per_car")
    private Integer numberOfSeatsPerCar;

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public Integer getNumberOfSeatsPerCar() {
        return numberOfSeatsPerCar;
    }

    public void setNumberOfSeatsPerCar(Integer numberOfSeatsPerCar) {
        this.numberOfSeatsPerCar = numberOfSeatsPerCar;
    }
}