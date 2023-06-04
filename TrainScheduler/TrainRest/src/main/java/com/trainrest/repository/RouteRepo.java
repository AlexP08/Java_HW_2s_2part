package com.trainrest.repository;

import com.trainrest.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для Рейса
 * @author Alexander Pavlov
 */
@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {
}