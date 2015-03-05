
package org.kantega.metrics.workshop;

import org.apache.commons.io.IOUtils;
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

    public WorkshopUiPlugin(Reststop reststop, WebjarsVersions webjarsVersions, TemplateRenderer templateRenderer) {

        addServletFilter(reststop.createServletFilter(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                final Map<String, String> versions = webjarsVersions.getVersions();
                resp.setContentType("text/html");
                if(false) {
                    String html = IOUtils.toString(getClass().getResourceAsStream("index.html"), "utf-8");
                    for (String version : versions.keySet()) {
                        String prop = "${" + version + "}";
                        html = html.replace(prop, versions.get(version));
                    }

                    IOUtils.write(html, resp.getOutputStream());
                } else {
                    TemplateRenderer.Renderer renderer = templateRenderer
                            .template("org/kantega/metrics/workshop/index.html", Charset.forName("utf-8"));
                    versions.entrySet().forEach((e) -> renderer.addAttribute(e.getKey().replace('.','_'), e.getValue()));
                    renderer.render(resp.getWriter());
                }

            }
        }, "/workshop-ui/"));


    }
}
