package org.kantega.metrics.api.dao;


import org.kantega.metrics.api.Blog;

import java.util.List;

/**
 * Database abstraction layer.
 */
public interface BlogDao {

    /**
     * Return all blogs.
     * 
     * @return list of all blogs ordered by name 
     */
    List<Blog> getAllBlogs();


    void saveOrUpdate(Blog blog);

    /**
     * Return a blog given an unique name.
     * 
     * @param blogName The name of the blog
     * @return Blog with the given name
     * @throws IllegalArgumentException If no blog with the given name can be found
     */
    Blog getBlogByName(String blogName);
        /**
     * Return a blog given the id of the blog.
     * 
     * @param blogId The id of the blog
     * @return Blog with the given id
     * @throws IllegalArgumentException If no blog with the given name can be found
     */
    Blog getBlogById(long blogId);

    /**
     * Delete a blog given its name.
     * 
     * @param blogName The name of the blog to delete
     */
    void deleteBlogByName(String blogName);

}
