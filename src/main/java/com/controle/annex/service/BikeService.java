package com.controle.annex.service;

import com.controle.annex.entities.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeService {

    Bike saveBike(Bike bike);

    Bike updateBike(Bike bike);

    Optional<Bike> findByIdBike(Long id);

    List<Bike> findAllBike();

    Bike deleteBike(Long id);
}
