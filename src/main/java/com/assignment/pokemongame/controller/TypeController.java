package com.assignment.pokemongame.controller;

import com.assignment.pokemongame.model.Type;
import com.assignment.pokemongame.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gameservice/v1/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping(value = "/{idOrName}")
    private ResponseEntity getAllPokemon(@PathVariable String idOrName){
        Type response = typeService.getTypeDetails(idOrName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
