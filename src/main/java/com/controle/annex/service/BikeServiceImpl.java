package com.controle.annex.service;

import com.controle.annex.entities.Bike;
import com.controle.annex.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository repository;

    @Override
    public Bike saveBike(Bike bike) {
        bike.setId(null);
        return repository.save(bike);
    }

    @Override
    public Bike updateBike(Bike bike) {
        if(bike.getId() != null){
         return repository.save(bike);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Bike> findByIdBike(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bike> findAllBike() {
        return repository.findAll();
    }

    @Override
    public Bike deleteBike(Long id) {
        Optional<Bike> findBike = repository.findById(id);
        if(findBike.isPresent()){
            repository.deleteById(findBike.get().getId());
        }
        return null;
    }
}
