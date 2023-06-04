package com.example.BookerRest.controller;

import com.example.BookerRest.model.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/extra-services")
public class ExtraServiceController {
    @Autowired
    private com.example.BookerRest.service.ExtraServiceService extraServiceService;

    @GetMapping("")
    public List<ExtraService> getAllExtraServices(){
        return extraServiceService.getAllExtraServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraService> get(@PathVariable Integer id){
        try{
            ExtraService extraService = extraServiceService.getExtraServiceById(id);
            return new ResponseEntity<ExtraService>(extraService, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<ExtraService>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public List<ExtraService> getByName(@PathVariable String name){
        return extraServiceService.getExtraServiceByName(name);
    }

    @GetMapping("/price-per-day/{pricePerDay}")
    public List<ExtraService> getByPricePerDay(@PathVariable Double pricePerDay){
        return extraServiceService.getExtraServiceByPricePerDay(pricePerDay);
    }

    @PostMapping("/")
    public void add(@RequestBody ExtraService extraService){
        extraServiceService.saveExtraService(extraService);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        extraServiceService.deleteExtraService(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ExtraService additionalService, @PathVariable Integer id){
        try{
            ExtraService baseAdditionalService = extraServiceService.getExtraServiceById(id);
            baseAdditionalService.updateAdditionalService(additionalService);
            extraServiceService.saveExtraService(baseAdditionalService);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
