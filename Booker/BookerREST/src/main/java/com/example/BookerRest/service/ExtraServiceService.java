package com.example.BookerRest.service;

import com.example.BookerRest.model.ExtraService;
import com.example.BookerRest.repository.ExtraServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExtraServiceService {
    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    public List<ExtraService> getAllExtraServices(){
        return extraServiceRepository.findAll();
    }

    public List<ExtraService> getExtraServiceByName(String name){
        return extraServiceRepository.findByName(name);
    }

    public List<ExtraService> getExtraServiceByPricePerDay(Double pricePerDay){
        return extraServiceRepository.findByPricePerDay(pricePerDay);
    }

    public void saveExtraService(ExtraService additionalService){
        extraServiceRepository.save(additionalService);
    }

    public void deleteExtraService(Integer id){
        extraServiceRepository.deleteById(id);
    }

    public ExtraService getExtraServiceById(Integer id){
        return extraServiceRepository.findById(id).orElse(null);
    }
}
