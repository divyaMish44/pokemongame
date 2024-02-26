package com.assignment.pokemongame.Utility;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        // Do any additional configuration here
//        return builder.build();
//    }

        @Bean
    public RestTemplate getRestTemplate() {
        // Do any additional configuration here
        return new RestTemplate();
    }


    String baseURL = "https://pokeapi.co/api/v2/";

    public String getURL(String service, String id){
        String completeURLWithId = baseURL + service +"/"+ id;
        return completeURLWithId;
    }

}
