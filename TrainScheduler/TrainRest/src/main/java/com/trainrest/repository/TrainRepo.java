package com.trainrest.repository;

import com.trainrest.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для Поезда
 */
@Repository
public interface TrainRepo extends JpaRepository<Train, Long> {
}