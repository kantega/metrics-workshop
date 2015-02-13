package org.kantega.metrics.rest;

import org.kantega.metrics.api.Blog;
import org.kantega.metrics.api.dao.BlogDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 */
@Path("r/blogs")
public class BlogsResource {

    private final BlogDao blogDao;

    public BlogsResource(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> listBlogs() {
        return blogDao.getAllBlogs();
    }
}
