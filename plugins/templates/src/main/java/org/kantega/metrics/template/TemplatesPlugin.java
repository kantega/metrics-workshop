
package org.kantega.metrics.template;

import org.kantega.metrics.api.templates.TemplateRenderer;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;

@Plugin
public class TemplatesPlugin {


    @Export
    private final TemplateRenderer templateRenderer;

    public TemplatesPlugin() {
        templateRenderer = new VelicityTemplateRenderer();
    }

}
