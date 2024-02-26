package com.assignment.pokemongame.service;

import com.assignment.pokemongame.PokemongameApplication;
import com.assignment.pokemongame.Utility.AppConfig;
import com.assignment.pokemongame.apiclient.HttpHelper;
import com.assignment.pokemongame.model.Pokemon;
import com.assignment.pokemongame.model.Species;
import com.assignment.pokemongame.model.Type;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PokemonService {

    @Autowired
    HttpHelper httpHelper;

    @Autowired
    Gson gson;

    @Autowired
    AppConfig appConfig;



    public Pokemon getPokemonDetails(String id){

        String URL = appConfig.getURL("pokemon", id);
        String responseJSON = httpHelper.callThirdPartyApi(URL);
        Pokemon pokemon = gson.fromJson(responseJSON, Pokemon.class);
        String speciesURL = pokemon.getSpecies().getUrl();
        String speciesResponseJSON = httpHelper.callThirdPartyApi(speciesURL);
        Species species1 = gson.fromJson(speciesResponseJSON, Species.class);
        pokemon.setSpecies(species1);
        return pokemon;
    }

    public List<Pokemon> getPokemonsByType(String idOrName) throws ExecutionException, InterruptedException {
        String url = appConfig.getURL("type", idOrName);
        String responseJson = httpHelper.callThirdPartyApi(url);
        Type typeResponse = gson.fromJson(responseJson, Type.class);
        List<Type.TypePokemon> typePokemon = typeResponse.getPokemon();
        List<Pokemon> response = new ArrayList<>();
        List<CompletableFuture<Pokemon>> futures = new ArrayList<>();

        for(Type.TypePokemon tp: typePokemon){
            String pokemonUrl = tp.getPokemon().getUrl();
            System.out.println("Divya in for >>>>>>>>>>>>>>>>>>>" + pokemonUrl);
            CompletableFuture futurePokemon = CompletableFuture.supplyAsync(() -> {
                        try {
                            return httpHelper.executeAsyncTask(pokemonUrl);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    })
//                    .thenApply(pokemon -> pokemon);
                    .thenAccept(pokemon -> {
                        System.out.println("completed call " + pokemon.getId());
                        response.add(pokemon);
                    });
//            System.out.println("Done ====================" + futurePokemon.get());
            futures.add(futurePokemon);
        }

//        for(CompletableFuture<Pokemon> f : futures) {
//            System.out.println("copying.......");
//            Pokemon resp = f.get();
//            response.add(resp);
//            System.out.println("copying id..." + resp.getId());
//        }
        CompletableFuture allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allFutures.get();
//        for(CompletableFuture<Pokemon> f : futures) {
//            System.out.println("copying.......");
//            Pokemon resp = f.get();
//            response.add(resp);
//            System.out.println("copying id..." + resp.getId());
//        }
//        CompletableFuture<List<>> finalFuture = allFutures.thenApply(v -> futures.stream().forEach(f -> response.add(f.get())));
//        finalFuture.get();

        return response;
    }
}
