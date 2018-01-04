package br.com.codecode.androidannotations.boot.rest.service;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by felipe on 03/01/18.
 */
@Rest(rootUrl = "https://api.github.com", converters = {MappingJackson2HttpMessageConverter.class})
public interface GithubClient extends RestClientHeaders {

    @Get("/search/repositories?q={searchString}")
    ResponseEntity<JSONObject> getResult(@Path String searchString);

    RestTemplate getRestTemplate();

}
