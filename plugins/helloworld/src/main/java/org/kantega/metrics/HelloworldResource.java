package org.kantega.metrics;

import com.codahale.metrics.MetricRegistry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 */
@Path("helloworld")
public class HelloworldResource {

    private final MetricRegistry metricRegistry;

    public HelloworldResource(MetricRegistry metricRegistry) {

        this.metricRegistry = metricRegistry;
    }

    @GET
    @Produces({"application/json", "application/xml" })
    public Hello hello() {
        metricRegistry.counter("World").inc();
        metricRegistry.timer("World").getOneMinuteRate();
        return new Hello("Hello world");
    }
}
