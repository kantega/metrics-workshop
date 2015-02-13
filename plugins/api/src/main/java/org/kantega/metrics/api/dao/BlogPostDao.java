package org.kantega.metrics.api.dao;


import org.kantega.metrics.api.Blog;
import org.kantega.metrics.api.BlogPost;

import java.util.List;

/**
 * Data Access Object for blog posts.
 */
public interface BlogPostDao {

    /**
     * Saves a blog post.
     * 
     * @param post the blog post to save to the database 
     */
    public void saveOrUpdate(BlogPost post);

    /**
     * Return all blog posts for a given blog.
     * 
     * @param blog The blog to read all blog post for
     * @return List of blog posts in this blog
     */
    public List<BlogPost> getBlogPosts(Blog blog);

    /**
     * Return a blog post given an id of the blog post.
     * 
     * @param blogPostId The id of the blog post
     * @return The blog post with this ID
     */
    public BlogPost getBlogPost(long blogPostId);

    /**
     * Return a blog post given the blog and the name of the post.
     * 
     * @param blog The blog this post belongs to
     * @param postName The name of the blog post
     * @return The blog post if found
     */
    public BlogPost getBlogPost(Blog blog, String postName);
}
