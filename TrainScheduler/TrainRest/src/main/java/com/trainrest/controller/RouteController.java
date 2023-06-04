package com.trainrest.controller;

import com.trainrest.model.Route;
import com.trainrest.repository.RouteRepo;
import com.trainrest.utils.Filler;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;

import java.util.List;


/**
 * Controller для Рейса
 * @author Alexander Pavlov
 */
@RestController
@RequestMapping("routes/")
public class RouteController {
    private final RouteRepo routeRepository;

    public RouteController(RouteRepo routeRepository) {
        this.routeRepository = routeRepository;
    }

    /**
     * Преобразует переданную строку в Route и сохраняет объект в бд
     * @param line String
     */
    @PostMapping("/addRoute")
    void createRoute(@RequestBody String line) {
        JSONObject jsonObject = new JSONObject(line);
        Route route = Filler.fillRoute(jsonObject);
        this.routeRepository.save(route);
    }

    /**
     * Возвращает все найденнные в бд записи по рейсам
     * @return String
     */
    @GetMapping("/allRoutes")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    /**
     * Находит рейс по переданному id и удаляет его вместе со всем записями,
     * в которых данный объект являлся внешним ключем
     * @param id Long
     */
    @Transactional
    @DeleteMapping("/id={id}")
    public void deleteRoute(@PathVariable Long id) {
        this.routeRepository.deleteById(id);
    }

    /**
     * Преобразует переданную строку в RouteEntity и перезаписывает объект в бд
     * @param line String
     */
    @Transactional
    @PutMapping("/update")
    public void updateRoute(@RequestBody String line) {
        JSONObject jsonObject = new JSONObject(line);
        Route route = Filler.fillRoute(jsonObject);
        this.routeRepository.save(route);
    }
}