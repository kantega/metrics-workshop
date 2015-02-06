package org.kantega.metrics;

import com.codahale.metrics.MetricRegistry;
import org.kantega.reststop.jaxrsapi.DefaultJaxRsPlugin;

/**
 *
 */
public class ExamplePlugin extends DefaultJaxRsPlugin{

    public ExamplePlugin(MetricRegistry metricRegistry) {
        addJaxRsSingletonResource(new HelloworldResource(metricRegistry));
    }

}