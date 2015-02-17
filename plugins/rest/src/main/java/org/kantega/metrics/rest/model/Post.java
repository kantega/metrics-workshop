package org.kantega.metrics.rest.model;

import org.kantega.metrics.api.BlogPost;

import java.util.Date;

/**
 *
 */
public class Post {
    private final String title;
    private final String content;
    private final Date publishDate;

    public Post(BlogPost blogPost) {
        this.title = blogPost.getTitle();
        this.content = blogPost.getContent();
        this.publishDate= new Date(blogPost.getPublishDate().toEpochMilli());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
