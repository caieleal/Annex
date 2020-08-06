package com.controle.annex.service;

import com.controle.annex.entities.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Manufacturer saveManufac(Manufacturer manufacturer);

    Optional<Manufacturer> findByIdManufac(Long id);

    List<Manufacturer> findAllManufac();
}
