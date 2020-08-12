package com.controle.annex.controller;

import com.controle.annex.entities.Client;
import com.controle.annex.entities.Manufacturer;
import com.controle.annex.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annex")
public class ManufacturerController {

    @Autowired
    private ManufacturerService service;


    @GetMapping("/findManufac")
    private ResponseEntity<List<Manufacturer>> findManufacturers(){
        List<Manufacturer> listAll = service.findAllManufac();
        return ResponseEntity.status(HttpStatus.OK).body(listAll);
    }
    @GetMapping("/findManufac/{code}")
    private ResponseEntity<Manufacturer> findManufacturerById(@Valid @PathVariable("code") Long code){
        Optional<Manufacturer> findManufacturer = service.findByIdManufac(code);
        if(findManufacturer.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(findManufacturer.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/manuFac")
    private ResponseEntity<Manufacturer> saveManufacturer(@Valid @RequestBody Manufacturer manufacturer, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        service.saveManufac(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturer);
    }
    @PutMapping("/editManufac/{id}")
    private ResponseEntity<Manufacturer> editManufacturer(@Valid @PathVariable("id")Long id, @RequestBody Manufacturer manufac){
        Optional<Manufacturer> findManufac =  service.findByIdManufac(id);
        if(!findManufac.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        manufac.setId(findManufac.get().getId());
        service.updateManufac(manufac);
        return ResponseEntity.status(HttpStatus.OK).body(manufac);
    }
    @DeleteMapping("/deleteManufac/{id}")
    private ResponseEntity<Manufacturer> deleteManufact(@PathVariable("id")Long id){
        Optional<Manufacturer> deletManufac = service.findByIdManufac(id);
        if (deletManufac.isPresent()) {
            service.deleteManufacByid(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
