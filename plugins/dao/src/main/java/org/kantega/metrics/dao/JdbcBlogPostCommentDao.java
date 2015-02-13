package org.kantega.metrics.dao;

import org.kantega.metrics.api.BlogPost;
import org.kantega.metrics.api.BlogPostComment;
import org.kantega.metrics.api.dao.BlogPostCommentDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object for Blog Post Comments.
 */
public class JdbcBlogPostCommentDao implements BlogPostCommentDao  {

    private final JdbcTemplate template;

    /**
     * Create a new Data Access Object.
     * 
     * @param dataSource the connection to the database to talk with 
     */
    public JdbcBlogPostCommentDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
    
    /**
     * Saves a blog comment.
     * 
     * @param comment blog comment to save to the database
     */
    public void saveOrUpdate(BlogPostComment comment) {
        if (comment.isNew()) {
            template.update("INSERT INTO blogpostcomment (blogpostid, commentauthor, commentcontent, commentpublishdate) VALUES (?, ?, ?, ?)",
                    comment.getBlogPost().getBlogPostId(),
                    comment.getAuthor(),
                    comment.getContent(),
                    new Timestamp(System.currentTimeMillis()));
        } else {
            template.update("update blogpostcomment set commentauthor=?, commentcontent=?, commentpublishdate=? where blogpostcommentid=?",
                    comment.getAuthor(),
                    comment.getContent(),
                    new Date(comment.getPublishDate().toEpochMilli()),
                    comment.getBlogPostCommentId());
        }
    }

    /**
     * Return a list of comments for a given blog post.
     * 
     * @param post The blog post to read comments from
     * @return List of comments for this blog post.
     */
    public List<BlogPostComment> getComments(final BlogPost post) {
        String sql = "select blogpostcommentid, commentauthor, commentcontent, commentpublishdate from blogpostcomment where blogpostid=?";
        return template.query(sql, new RowMapper<BlogPostComment>() {
            @Override
            public BlogPostComment mapRow(ResultSet rs, int i) throws SQLException {
                BlogPostComment comment = new BlogPostComment(post);
                comment.setBlogPostCommentId(rs.getLong("blogpostcommentid"));
                comment.setAuthor(rs.getString("commentauthor"));
                comment.setContent(rs.getString("commentcontent"));
                comment.setPublishDate(rs.getTimestamp("commentpublishdate"));
                return comment;
            }
        }, post.getBlogPostId());
    }

}