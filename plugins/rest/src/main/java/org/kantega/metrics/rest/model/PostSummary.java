package org.kantega.metrics.rest.model;

import java.util.Date;

/**
 *
 */
public class PostSummary {
    private final String title;
    private final String content;
    private final Date publishDate;
    private final int commentCount;

    public PostSummary(String title, String content, Date publishDate, int commentCount) {
        this.title = title;
        this.content = content;

        this.publishDate = publishDate;
        this.commentCount = commentCount;
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

    public int getCommentCount() {
        return commentCount;
    }
}
