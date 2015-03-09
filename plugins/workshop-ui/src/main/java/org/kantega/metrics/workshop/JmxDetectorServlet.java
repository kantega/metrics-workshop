package org.kantega.metrics.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JmxDetectorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();

        Map<Object, Object> result = new HashMap<>();

        result.put("domains", beanServer.getDomains());

        List<String> metricsBeans = new ArrayList<>();

        result.put("metricsBeans", metricsBeans);


        for (ObjectName objectName : beanServer.queryNames(null, null)) {
            if("metrics".equals(objectName.getDomain())) {
                metricsBeans.add(objectName.getKeyProperty("name"));
            }
        }

        new ObjectMapper().writer().writeValue(resp.getOutputStream(), result);

    }
}
