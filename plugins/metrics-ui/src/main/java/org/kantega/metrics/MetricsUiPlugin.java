
package org.kantega.metrics;

import org.apache.commons.io.IOUtils;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Reststop;
import org.kantega.reststop.webjars.WebjarsVersions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MetricsUiPlugin extends DefaultReststopPlugin {

    public MetricsUiPlugin(Reststop reststop, WebjarsVersions webjarsVersions) {

        addServletFilter(reststop.createServletFilter(new HttpServlet() {
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
        }, "/metrics-ui/"));

        addServletFilter(reststop.createServletFilter(new javax.servlet.http.HttpServlet() {
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
        }, "/chart.html"));

        addServletFilter(reststop.createServletFilter(new javax.servlet.http.HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/javascript");
                IOUtils.copy(getClass().getResourceAsStream("/chart.js"), resp.getOutputStream());
            }
        }, "/chart.js"));

    }
}

