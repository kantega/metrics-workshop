
package org.kantega.metrics;

import org.apache.commons.io.IOUtils;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Reststop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class MetricsUiPlugin extends DefaultReststopPlugin {

    public MetricsUiPlugin(Reststop reststop) {

        final Map<String, String> versions = getVersionsForWebJars();

        addServletFilter(reststop.createServletFilter(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/html");
                String html = IOUtils.toString(getClass().getResourceAsStream("index.html"), "utf-8");
                for (String version : versions.keySet()) {
                    String prop = "${" + version + "}";
                    html = html.replace(prop, versions.get(version));
                }

                IOUtils.write(html, resp.getOutputStream());

            }
        }, "/"));
        addServletFilter(reststop.createServletFilter(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String filename = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
                resp.setContentType(req.getServletContext().getMimeType(filename));
                String path = req.getRequestURI().substring(req.getContextPath().length() + req.getServletPath().length());
                InputStream resourceAsStream = getClass().getResourceAsStream("/META-INF/resources/webjars" + path);
                if (resourceAsStream == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    IOUtils.copy(resourceAsStream, resp.getOutputStream());
                }
            }
        }, "/webjars/*"));

    }

    private Map<String, String> getVersionsForWebJars() {
        HashMap<String, String> versions = new HashMap<>();

        Set<String> webjars = new HashSet<>();

        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/resources/webjars/");
            while (resources.hasMoreElements()) {
                URL webJar = resources.nextElement();
                String file = URLDecoder.decode(webJar.getFile(), "UTF-8");
                file = file.substring(0, file.indexOf("!"));
                webjars.add(file);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String webjar : webjars) {
            String file = webjar.substring(0, webjar.lastIndexOf("/"));

            String version = file.substring(file.lastIndexOf("/") + 1);
            String artifact = file.substring(0, file.lastIndexOf("/"));
            String artifactId = artifact.substring(artifact.lastIndexOf("/") + 1);

            if (version.contains("-")) {
                version = version.substring(0, version.lastIndexOf("-"));
            }

            versions.put("versions." + artifactId, version);
        }

        return versions;

    }
}

