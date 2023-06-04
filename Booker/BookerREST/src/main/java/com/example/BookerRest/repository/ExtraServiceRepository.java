package com.example.BookerRest.repository;

import com.example.BookerRest.model.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraServiceRepository extends JpaRepository<ExtraService, Integer> {
        List<ExtraService> findByName(String name);
        List<ExtraService> findByPricePerDay(Double pricePerDay);
}
