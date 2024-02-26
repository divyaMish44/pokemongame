package com.assignment.pokemongame.apiclient;
import com.assignment.pokemongame.model.Pokemon;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class HttpHelper {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    public HttpHelper(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> T callThirdPartyApi(String url, Class<T> ResponseType) {
        T result = restTemplate.getForObject(url, ResponseType);
        return result;
    }

    public String callThirdPartyApi(String url) {
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

//    @Async
//    public CompletableFuture<Pokemon> executeAsyncTask(String pokemonUrl) throws InterruptedException {
//        System.out.println("Api call to poke " + pokemonUrl);
//        String pokemonResponseJson = this.callThirdPartyApi(pokemonUrl);
//        Pokemon pokemon = gson.fromJson(pokemonResponseJson, Pokemon.class);
//        System.out.println("Api call Done " + pokemonUrl);
//        return CompletableFuture.completedFuture(pokemon);
//    }

    public Pokemon executeAsyncTask(String pokemonUrl) throws InterruptedException {
        System.out.println("Api call to poke " + pokemonUrl);
        Pokemon pokemon = this.callThirdPartyApi(pokemonUrl, Pokemon.class);
//        Pokemon pokemon = gson.fromJson(pokemonResponseJson, Pokemon.class);
        System.out.println("Api call Done " + pokemonUrl);
        return pokemon;
    }
}
