package org.kantega.metrics.api;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Domain object representing a comment on a given blog post.
 * 
 * A comment belongs to a single blog post, but a blog post can have 
 * many comments.
 */
public class BlogPostComment {

    private final BlogPost blogPost;
    private long blogPostCommentId;
    private String author;
    private String content;
    private Instant publishDate;

    /**
     * Create a new comment on a given blog post.
     * 
     * @param blogPost The post this is a comment on 
     */
    public BlogPostComment(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public long getBlogPostCommentId() {
        return blogPostCommentId;
    }

    public void setBlogPostCommentId(long blogPostCommentId) {
        this.blogPostCommentId = blogPostCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns true if this is a new comment - that is not stored to the database yet.
     * 
     * @return true if this comment has not been saved to the database
     */
    public boolean isNew() {
        return blogPostCommentId == 0;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public Instant getPublishDate() {
        return publishDate;
    }


    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = Instant.ofEpochMilli(publishDate.getTime());
    }
}
