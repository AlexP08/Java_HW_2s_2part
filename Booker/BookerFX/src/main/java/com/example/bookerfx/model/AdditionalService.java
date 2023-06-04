package com.example.bookerfx.model;

public class AdditionalService {
    private Integer id;
    private String name;
    private Double pricePerDay;

    public AdditionalService() {
    }

    public AdditionalService(String name, Double pricePerDay) {
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
