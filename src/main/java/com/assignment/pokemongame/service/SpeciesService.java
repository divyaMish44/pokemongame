package com.assignment.pokemongame.service;

import com.assignment.pokemongame.Utility.AppConfig;
import com.assignment.pokemongame.apiclient.HttpHelper;
import com.assignment.pokemongame.model.Species;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService {

    @Autowired
    AppConfig appConfig;

    @Autowired
    Gson gson;

    @Autowired
    HttpHelper httpHelper;

    public Species getSpecies(String id){
        String speciesURL = appConfig.getURL("pokemon-species", id);
        String responseJson = httpHelper.callThirdPartyApi(speciesURL);
        Species species = gson.fromJson(responseJson, Species.class);
        return species;
    }
}
