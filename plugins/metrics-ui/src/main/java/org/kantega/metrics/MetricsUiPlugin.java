
package org.kantega.metrics;

import org.apache.commons.io.IOUtils;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;
import org.kantega.reststop.api.ServletBuilder;
import org.kantega.reststop.webjars.WebjarsVersions;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Plugin
public class MetricsUiPlugin {

    @Export
    private final Filter indexFilter;

    public MetricsUiPlugin(final ServletBuilder servletBuilder, WebjarsVersions webjarsVersions) {

        HttpServlet indexServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                final Map<String, String> versions = webjarsVersions.getVersions();
                resp.setContentType("text/html");
                String html = IOUtils.toString(getClass().getResourceAsStream("/index.html"), "utf-8");
                for (String version : versions.keySet()) {
                    String prop = "${" + version + "}";
                    html = html.replace(prop, versions.get(version));
                }

                IOUtils.write(html, resp.getOutputStream());

            }
        };

        indexFilter = servletBuilder.servlet(indexServlet, "/metrics-ui/");

    }
}

