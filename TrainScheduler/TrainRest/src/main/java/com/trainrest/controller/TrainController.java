package com.trainrest.controller;

import com.trainrest.model.Train;
import com.trainrest.repository.TrainRepo;
import com.trainrest.utils.Filler;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Controller для Поезда
 * @author Alexander Pavlov
 */
@RestController
@RequestMapping("trains/")
public class TrainController {
    private final TrainRepo trainRepository;

    public TrainController(TrainRepo trainRepository) {
        this.trainRepository = trainRepository;
    }

    /**
     * Преобразует переданную строку в TrainEntity и сохраняет объект в бд
     * @param line String
     */
    @PostMapping("/addTrain")
    void createTrain(@RequestBody String line) {
        JSONObject jsonObject = new JSONObject(line);
        Train train = Filler.fillTrain(jsonObject);
        this.trainRepository.save(train);
    }

    /**
     * Возвращает все найденнные в бд записи по поездам
     * @return String
     */
    @GetMapping("/allTrains")
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    /**
     * Находит поезд по переданному id и удаляет его вместе со всем записями,
     * в которых данный объект являлся внешним ключем
     * @param id Long
     */
    @Transactional
    @DeleteMapping("/id={id}")
    public void deleteTrain(@PathVariable Long id) {
        this.trainRepository.deleteById(id);
    }

    /**
     * Преобразует переданную строку в TrainEntity и перезаписывает объект в бд
     * @param line String
     */
    @Transactional
    @PutMapping("/update")
    public void updateTrain(@RequestBody String line) {
        JSONObject jsonObject = new JSONObject(line);
        Train train = Filler.fillTrain(jsonObject);
        this.trainRepository.save(train);
    }
}