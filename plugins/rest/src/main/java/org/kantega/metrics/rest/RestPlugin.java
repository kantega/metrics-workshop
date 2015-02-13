
package org.kantega.metrics.rest;

import org.kantega.metrics.api.dao.BlogDao;
import org.kantega.reststop.jaxrsapi.DefaultJaxRsPlugin;

public class RestPlugin extends DefaultJaxRsPlugin {


    public RestPlugin(BlogDao blogDao) {
        addJaxRsSingletonResource(new BlogsResource(blogDao));
    }

}
