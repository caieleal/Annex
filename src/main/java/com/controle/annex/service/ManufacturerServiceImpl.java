package com.controle.annex.service;

import com.controle.annex.entities.Manufacturer;
import com.controle.annex.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository repository;

    @Override
    public Manufacturer saveManufac(Manufacturer manufacturer) {
        return repository.save(manufacturer);
    }

    @Override
    public Optional<Manufacturer> findByIdManufac(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Manufacturer> findAllManufac() {
        return repository.findAll();
    }
}
