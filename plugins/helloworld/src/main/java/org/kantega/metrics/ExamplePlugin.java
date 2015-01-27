package org.kantega.metrics;

import org.kantega.reststop.jaxrsapi.DefaultJaxRsPlugin;

/**
 *
 */
public class ExamplePlugin extends DefaultJaxRsPlugin{

    public ExamplePlugin() {
        addJaxRsSingletonResource(new HelloworldResource());
    }

}