
package org.kantega.metrics.rest;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.api.dao.BlogDao;
import org.kantega.metrics.api.dao.BlogPostCommentDao;
import org.kantega.metrics.api.dao.BlogPostDao;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;
import org.kantega.reststop.jaxrsapi.ApplicationBuilder;

import javax.ws.rs.core.Application;

@Plugin
public class RestPlugin  {

    @Export
    private final Application metricsApp;

    public RestPlugin(BlogDao blogDao, BlogPostDao blogPostDao, BlogPostCommentDao blogPostCommentDao, MetricRegistry metricRegistry, ApplicationBuilder applicationBuilder) {

        metricsApp = applicationBuilder.application()
                .singleton(new BlogsResource(blogDao, blogPostDao, blogPostCommentDao, metricRegistry))
                .build();
    }

}
