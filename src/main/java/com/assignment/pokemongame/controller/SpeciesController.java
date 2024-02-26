package com.assignment.pokemongame.controller;

import com.assignment.pokemongame.model.Species;
import com.assignment.pokemongame.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameservice/v1/species")
public class SpeciesController {

    @Autowired
    SpeciesService speciesService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getSpecies(@PathVariable int id){
        Species response = speciesService.getSpecies(Integer.toString(id));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
