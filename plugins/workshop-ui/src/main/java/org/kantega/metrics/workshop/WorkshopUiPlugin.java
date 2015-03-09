
package org.kantega.metrics.workshop;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.api.ThreadLocalContext;
import org.kantega.metrics.api.templates.TemplateRenderer;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.FilterPhase;
import org.kantega.reststop.api.Reststop;
import org.kantega.reststop.webjars.WebjarsVersions;

import javax.servlet.*;
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

    public WorkshopUiPlugin(Reststop reststop, WebjarsVersions webjarsVersions, TemplateRenderer templateRenderer, MetricRegistry metricRegistry) {
        this.reststop = reststop;
        this.webjarsVersions = webjarsVersions;
        this.templateRenderer = templateRenderer;

        templateServlet("index.html", "/workshop-ui/");
        templateServlet("gettingstarted.html", "/workshop-ui/gettingstarted");
        templateServlet("addcounter.html", "/workshop-ui/addcounter");
        templateServlet("timeblogposts.html", "/workshop-ui/timeblogposts");
        templateServlet("memoryusage.html", "/workshop-ui/memoryusage");
        templateServlet("memory_over_time.html", "/workshop-ui/memory_over_time");
        templateServlet("jmx.html", "/workshop-ui/jmx");
        templateServlet("graphite.html", "/workshop-ui/graphite");

        addServletFilter(reststop.createFilter(new SlowFilter(), "/r/*", FilterPhase.PRE_AUTHENTICATION));

        addServletFilter(reststop.createServletFilter(new JmxDetectorServlet(), "/jmxdetector"));
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

    private class SlowFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            try {
                String header = req.getHeader("X-GetPostsDelay");
                if(header != null) {
                    ThreadLocalContext.set("getPostsDelay", Long.parseLong(header));
                }
                chain.doFilter(req, resp);
            } finally {
                ThreadLocalContext.clear("getPostsDelay");
            }
        }

        @Override
        public void destroy() {

        }
    }
}
