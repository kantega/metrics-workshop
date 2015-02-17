package org.kantega.metrics.rest.model;

import org.kantega.metrics.api.BlogPostComment;

import java.util.Date;

/**
 *
 */
public class Comment {

    private final String author;
    private final String content;
    private final Date publishDate;

    public Comment(BlogPostComment c) {
        author = c.getAuthor();
        content = c.getContent();
        publishDate = new Date(c.getPublishDate().toEpochMilli());
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
