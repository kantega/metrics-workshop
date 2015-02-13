package org.kantega.metrics.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Domain object for a blog.
 * 
 * A blog can have several blog posts.
 */
public class Blog {
    private long id;
    private String name;
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return true if this blog post is new (that is not stored to the database yet).
     * 
     * @return true if this blog post it not stored to the database yet
     */
    public boolean isNew() {
        return id == 0;
    }

    /**
     * Returns the id used in the link to this blog.
     * 
     * @return the id used in the link to this blog
     */
    public String getLinkId()  {
        return (name == null) ? null : getUrlEncodedName();
    }
    
    private String getUrlEncodedName() {
        try {
            return URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
