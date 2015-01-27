package org.kantega.metrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 */
@Path("helloworld")
public class HelloworldResource {

    @GET
    @Produces({"application/json", "application/xml" })
    public Hello hello() {
        return new Hello("Hello world");
    }
}
