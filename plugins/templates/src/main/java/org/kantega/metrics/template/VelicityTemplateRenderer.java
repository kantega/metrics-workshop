package org.kantega.metrics.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.kantega.metrics.api.templates.TemplateRenderer;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class VelicityTemplateRenderer implements TemplateRenderer {

    private final VelocityEngine velocityEngine;

    public VelicityTemplateRenderer() {
        this.velocityEngine = new VelocityEngine();
        velocityEngine.addProperty("resource.loader", "class");
        velocityEngine.addProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.addProperty("userdirective", SectionDirective.class.getName());
        velocityEngine.addProperty("eventhandler.include.class", IncludeRelativePath.class.getName());
    }

    @Override
    public Renderer template(String path, Charset charset) {
        Template template = velocityEngine.getTemplate(path, charset.name());
        return new DefaultRenderer(template);
    }

    private class DefaultRenderer implements Renderer {
        private final Template template;

        private Map<String, Object> attributes = new HashMap<>();

        public DefaultRenderer(Template template) {
            this.template = template;
        }

        @Override
        public Renderer addAttribute(String name, Object value) {
            attributes.put(name, value);
            return this;
        }

        @Override
        public Renderer addAttributes(Map<String, String> versions) {
            versions.forEach(this::addAttribute);
            return this;
        }

        @Override
        public String render() {
            StringWriter stringWriter = new StringWriter();
            render(stringWriter);
            return stringWriter.toString();
        }

        @Override
        public void render(Writer writer) {
            template.merge(new VelocityContext(attributes), writer);
        }
    }
}
