package com.controle.annex.controller;

import com.controle.annex.entities.Bike;
import com.controle.annex.entities.Manufacturer;
import com.controle.annex.service.BikeService;
import com.controle.annex.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annex")
public class BikeController {

    @Autowired
    private BikeService service;

    @Autowired
    private ManufacturerService manuService;

    @PostMapping("/saveBike/{manufacturer}")
    private ResponseEntity<Bike> saveBike(@PathVariable("manufacturer")Long manufacturer, @Valid @RequestBody Bike bike){
        Optional<Manufacturer> findManufac = manuService.findByIdManufac(manufacturer);
        if(findManufac.isPresent()){
            bike.setManufacturer(findManufac.get());
            service.saveBike(bike);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{manufacturer}").build().toUri();
            return ResponseEntity.created(uri).build();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/updateBike/{code}")
    private ResponseEntity<Bike> updateBike(@PathVariable("code")Long code, @Valid @RequestBody Bike bike){
        Optional<Bike> findBike = service.findByIdBike(code);
        if(!findBike.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bike.setId(findBike.get().getId());
        service.updateBike(bike);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").build().toUri();
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @GetMapping("/listBikes")
    private ResponseEntity<List<Bike>> listBikes(){
        List<Bike> findBikes = service.findAllBike();
        return ResponseEntity.ok().body(findBikes);
    }
    @GetMapping("/findBike/{id}")
    private ResponseEntity<Bike> findBike(@PathVariable("id") Long id){
        Optional<Bike> findBike = service.findByIdBike(id);
        if(findBike.isPresent()){
            return new ResponseEntity<>(findBike.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteBike/{id}")
    private ResponseEntity<Bike> deleteBike(@PathVariable("id")Long id){
        Optional<Bike> findBike = service.findByIdBike(id);
        if(findBike.isPresent()){
            service.deleteBike(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
