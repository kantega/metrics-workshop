
package org.kantega.metrics.rest;

import org.kantega.metrics.api.dao.BlogDao;
import org.kantega.metrics.api.dao.BlogPostDao;
import org.kantega.reststop.jaxrsapi.DefaultJaxRsPlugin;

public class RestPlugin extends DefaultJaxRsPlugin {


    public RestPlugin(BlogDao blogDao, BlogPostDao blogPostDao) {
        addJaxRsSingletonResource(new BlogsResource(blogDao, blogPostDao));
    }

}
