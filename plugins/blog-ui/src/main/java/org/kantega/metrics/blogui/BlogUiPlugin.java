
package org.kantega.metrics.blogui;

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
public class BlogUiPlugin {

    @Export
    private final Filter indexFilter;

    @Export
    private final Filter chartFilter;

    @Export
    private final Filter chartJsFilter;

    public BlogUiPlugin(final ServletBuilder servletBuilder, WebjarsVersions webjarsVersions) {

        HttpServlet indexServlet = new javax.servlet.http.HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                final Map<String, String> versions = webjarsVersions.getVersions();
                resp.setContentType("text/html");
                String html = IOUtils.toString(getClass().getResourceAsStream("index.html"), "utf-8");
                for (String version : versions.keySet()) {
                    String prop = "${" + version + "}";
                    html = html.replace(prop, versions.get(version));
                }

                IOUtils.write(html, resp.getOutputStream());
            }
        };

        indexFilter = servletBuilder.servlet(indexServlet, "/");

        HttpServlet chartServlet = new javax.servlet.http.HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/html");
                final Map<String, String> versions = webjarsVersions.getVersions();
                String html = IOUtils.toString(getClass().getResourceAsStream("/chart.html"), "utf-8");
                for (String version : versions.keySet()) {
                    String prop = "${" + version + "}";
                    html = html.replace(prop, versions.get(version));
                }

                IOUtils.write(html, resp.getOutputStream());
            }
        };

        chartFilter = servletBuilder.servlet(chartServlet, "/chart.html");

        HttpServlet chartJsServlet = new javax.servlet.http.HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/javascript");
                IOUtils.copy(getClass().getResourceAsStream("/chart.js"), resp.getOutputStream());
            }
        };

        chartJsFilter = servletBuilder.servlet(chartJsServlet, "/chart.js");
    }
}
