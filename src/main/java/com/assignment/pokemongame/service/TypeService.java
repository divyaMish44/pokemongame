package com.assignment.pokemongame.service;

import com.assignment.pokemongame.Utility.AppConfig;
import com.assignment.pokemongame.apiclient.HttpHelper;
import com.assignment.pokemongame.model.Pokemon;
import com.assignment.pokemongame.model.Type;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    HttpHelper httpHelper;

    @Autowired
    AppConfig appConfig;

    @Autowired
    Gson gson;

    public Type getTypeDetails(String idOrName){
        String url = appConfig.getURL("type", idOrName);
        Type response = httpHelper.callThirdPartyApi(url, Type.class);
//        Type response = gson.fromJson(responseJson, Type.class);
        return response;
    }


}
