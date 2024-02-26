package com.assignment.pokemongame.controller;

import com.assignment.pokemongame.apiclient.HttpHelper;
import com.assignment.pokemongame.model.Pokemon;
import com.assignment.pokemongame.service.PokemonService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/gameservice/v1/pokemon")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @Autowired
    Gson gson;

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
////    public String getPokemonById(@PathVariable int id){
//    public ResponseEntity getPokemonById(@PathVariable int id){
////        return pokemonService.getPokemonDetails(id);
//        String response = gson.toJson(pokemonService.getPokemonDetails(Integer.toString(id)));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getPokemon(@PathVariable int id){
    public ResponseEntity getPokemonByName(@PathVariable String name){
//        return pokemonService.getPokemonDetails(id);
        String response = gson.toJson(pokemonService.getPokemonDetails(name));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getPokemonsByType(@RequestParam(value = "type", required = false) String idOrName) throws ExecutionException, InterruptedException {
//        return pokemonService.getPokemonDetails(id);
        List<Pokemon> response = pokemonService.getPokemonsByType(idOrName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
