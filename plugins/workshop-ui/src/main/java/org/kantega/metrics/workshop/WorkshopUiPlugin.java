
package org.kantega.metrics.workshop;

import org.kantega.metrics.api.templates.TemplateRenderer;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Reststop;
import org.kantega.reststop.webjars.WebjarsVersions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class WorkshopUiPlugin extends DefaultReststopPlugin {

    private final Reststop reststop;
    private final WebjarsVersions webjarsVersions;
    private final TemplateRenderer templateRenderer;

    public WorkshopUiPlugin(Reststop reststop, WebjarsVersions webjarsVersions, TemplateRenderer templateRenderer) {
        this.reststop = reststop;
        this.webjarsVersions = webjarsVersions;
        this.templateRenderer = templateRenderer;

        templateServlet("index.html", "/workshop-ui/");
        templateServlet("gettingstarted.html", "/workshop-ui/gettingstarted");


    }

    private void templateServlet(String file, String path) {
        addServletFilter(reststop.createServletFilter(new TemplateServlet(file), path));
    }

    class TemplateServlet extends HttpServlet {

        private final String file;

        TemplateServlet(String file) {
            this.file = file;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            final Map<String, String> versions = webjarsVersions.getVersions();
            resp.setContentType("text/html");
            TemplateRenderer.Renderer renderer = templateRenderer
                    .template("org/kantega/metrics/workshop/" + file, Charset.forName("utf-8"));
            versions.entrySet().forEach((e) -> renderer.addAttribute(e.getKey().replace('.', '_'), e.getValue()));
            renderer.render(resp.getWriter());

        }
    }
}
