package dev.sampsy;

import io.micronaut.http.annotation.Get;

@io.micronaut.http.annotation.Controller
public class Controller {

    private final EndpointClient client;

    public Controller(EndpointClient client) {
        this.client = client;
    }

    /*
    What I want to do:

        User hits endpoint
        Make a request to some other api
        Process the response
        Send an appropriate response back to the user
     */


    @Get("/")
    Object returnData() {
        /*
        Based this implementation on the "Declarative Client" from https://guides.micronaut.io/latest/micronaut-http-client-maven-java.html
        I stripped out the configuration though, trying to keep it as simple as possible.

        The example doesn't process the response in any way though and I wasn't sure how to work with a Publisher so I usded RXJava3's Observable instead.
        That's got me closer to a solution but it still doesn't do what I want - it just returns "disposable :true/false"

        I'm also not even sure why we use a reactive client for single requests like this as it seems like a simpler async request would do
        */

        var something = client.fetchReleases();

        var observer = something.subscribe(
            data -> processResponse(data),
            error -> System.out.println("There's an error: " + error.getMessage()),
            () -> System.out.println("Responsive request completed")
        );

        // I'm trying to return the value from processResponse here but don't know how
        return observer;

    }

    @Get("/simple")
        // This works but looks very different and I have no idea if it's correct
    Object returnDataSimple() {
        var response = client.fetchReleases().blockingFirst();
        return processResponse(response);
    }

    public Object processResponse(Object response){
        System.out.println(response);
        return response;
    }
}
