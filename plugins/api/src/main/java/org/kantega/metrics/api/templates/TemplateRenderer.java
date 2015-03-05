package org.kantega.metrics.api.templates;

import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 *
 */
public interface TemplateRenderer {

    TemplateRenderer.Renderer template(String name, Charset charset);

    static interface Renderer {

        Renderer addAttribute(String name, Object value);

        Renderer addAttributes(Map<String, String> versions);

        String render();

        void render(Writer writer);
    }
}