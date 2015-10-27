package org.kantega.reststop.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.*;
import com.codahale.metrics.servlets.MetricsServlet;
import com.sun.net.httpserver.Filter;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;
import org.kantega.reststop.api.ServletBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

@Plugin
public class MetricsPlugin {

    @Export
    private Filter metricsFilter;

    @Export
    private final MetricRegistry metricRegistry;
    private JmxReporter jmxReporter;

    public MetricsPlugin(final ServletBuilder servletBuilder, ServletContext servletContext) throws ServletException {

        metricRegistry = initMetricsRegistry();
        MetricsServlet metricsServlet = new MetricsServlet(metricRegistry);
        metricsServlet.init(new MetricsServletConfig(servletContext));

        // TODO: Stop the jmxReporter
        servletBuilder.servlet(metricsServlet, "/metrics/*");

    }

    private MetricRegistry initMetricsRegistry() {
        MetricRegistry registry = new MetricRegistry();

        registry.registerAll(new MemoryUsageGaugeSet());
        registry.register("fileDescriptorRation", new FileDescriptorRatioGauge());
        registry.registerAll(new GarbageCollectorMetricSet());
        registry.registerAll(new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));
        registry.registerAll(new ThreadStatesGaugeSet());

        // TODO: Create and start the jmxReporter

        return registry;
    }

    //@Override
    //public void destroy() {
        // TODO: Stop the jmxReporter
    //}

    private class MetricsServletConfig implements ServletConfig {
        private final ServletContext servletContext;

        private MetricsServletConfig(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        @Override
        public String getServletName() {
            return "metrics";
        }

        @Override
        public ServletContext getServletContext() {
            return servletContext;
        }

        @Override
        public String getInitParameter(String name) {
            if (MetricsServlet.DURATION_UNIT.equals(name)) {
                return TimeUnit.MILLISECONDS.toString();
            }
            return null;
        }

        @Override
        public Enumeration<String> getInitParameterNames() {
            return Collections.emptyEnumeration();
        }
    }
}
