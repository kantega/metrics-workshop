
package org.kantega.metrics.template;

import org.kantega.metrics.api.templates.TemplateRenderer;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Export;

public class TemplatesPlugin extends DefaultReststopPlugin {


    @Export
    private final TemplateRenderer templateRenderer;

    public TemplatesPlugin() {
        templateRenderer = new VelicityTemplateRenderer();
    }

}
