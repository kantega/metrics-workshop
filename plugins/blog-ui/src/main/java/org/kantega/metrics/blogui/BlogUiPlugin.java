
package org.kantega.metrics.blogui;

import org.apache.commons.io.IOUtils;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Reststop;
import org.kantega.reststop.webjars.WebjarsVersions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BlogUiPlugin
    extends DefaultReststopPlugin
{


    public BlogUiPlugin(Reststop reststop, WebjarsVersions webjarsVersions) {
        addServletFilter(reststop.createServletFilter(new javax.servlet.http.HttpServlet() {
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
        }, "/"));
    }

}
