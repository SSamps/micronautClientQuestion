package dev.sampsy;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.rxjava3.core.Observable;

@Client("http://dummy.restapiexample.com/api/v1")
public interface EndpointClient {

    @Get("/employees")
    Observable<Object> fetchReleases();
}