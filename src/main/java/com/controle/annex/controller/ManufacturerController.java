package com.controle.annex.controller;

import com.controle.annex.entities.Manufacturer;
import com.controle.annex.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/annex")
public class ManufacturerController {

    @Autowired
    private ManufacturerService service;

    @PostMapping("/manuFac")
    private ResponseEntity<Manufacturer> saveManufacturer(@Valid @RequestBody Manufacturer manufacturer){
        service.saveManufac(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturer);
    }
    @GetMapping("/findManufac")
    private ResponseEntity<List<Manufacturer>> findManufacturers(){
        List<Manufacturer> listAll = service.findAllManufac();
        return ResponseEntity.status(HttpStatus.OK).body(listAll);
    }
}
