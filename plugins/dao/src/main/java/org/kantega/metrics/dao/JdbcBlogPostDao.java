package org.kantega.metrics.dao;

import org.kantega.metrics.api.Blog;
import org.kantega.metrics.api.BlogPost;
import org.kantega.metrics.api.dao.BlogPostDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Data Access Object for blog posts.
 */
public class JdbcBlogPostDao implements BlogPostDao {
    
    private final JdbcTemplate template;
    private final JdbcBlogDao jdbcBlogDao;

    /**
     * Create a new Data Access Object.
     * 
     * @param dataSource the connection to the database to talk with 
     */
    public JdbcBlogPostDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        jdbcBlogDao = new JdbcBlogDao(dataSource);
    }
    
    /**
     * Saves a blog post.
     * 
     * @param post the blog post to save to the database 
     */
    public void saveOrUpdate(BlogPost post) {
        if (post.isNew()) {
            template.update("INSERT INTO blogpost (blogid, posttitle, postcontent, publishdate) VALUES (?, ?, ?, ?)",
                    post.getBlog().getId(),
                    post.getTitle(),
                    post.getContent(),
                    new Timestamp(System.currentTimeMillis()));
        } else {
            template.update("update blogpost set posttitle=?, postcontent=? where blogpostid=?",
                    post.getTitle(),
                    post.getContent(),
                    post.getBlogPostId());
        }
    }

    /**
     * Return all blog posts for a given blog.
     * 
     * @param blog The blog to read all blog post for
     * @return List of blog posts in this blog
     */
    public List<BlogPost> getBlogPosts(Blog blog) {
        return getBlogPosts("where blogpost.blogid=?", blog.getId());
    }

    private List<BlogPost> getBlogPosts(String whereClaus, Object... parameters) {
        boolean fast = false;
        List<BlogPost> result;
        if (fast) {
            result = getBlogPostsEfficient(whereClaus, parameters);
        } else {
            result = getBlogPostsInefficient(whereClaus, parameters);
        }
        return result;
    }

    /**
     * Efficient way to return blog posts.
     */
    private List<BlogPost> getBlogPostsEfficient(String whereClaus, Object... parameters) {
        String sql = "select blog.blogid, blog.blogname, blog.color, blogpost.blogpostid, blogpost.posttitle, blogpost.postcontent, blogpost.publishdate, " +
                " (select count(*) from blogpostcomment where blogpostcomment.blogpostid=blogpost.blogpostid) as commentcount " +
                " from blogpost left join blog on blog.blogid=blogpost.blogid " + whereClaus + " order by  blogpost.blogpostid desc";

        return template.query(sql, new RowMapper<BlogPost>() {
            @Override
            public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
                Blog blog = JdbcBlogDao.getBlogFromResultSet(rs);
                BlogPost post = new BlogPost(blog);
                post.setBlogPostId(rs.getInt("blogpostid"));
                post.setTitle(rs.getString("posttitle"));
                post.setContent(rs.getString("postcontent"));
                post.setPublishDate(rs.getTimestamp("publishdate"));
                post.setCommentCount(rs.getInt("commentcount"));
                return post;
            }
        }, parameters);
    }

    /**
     * Inefficient way to return blog posts.
     */
    private List<BlogPost> getBlogPostsInefficient(String whereClaus, Object... parameters) {
        String sql = "select blogpost.blogid, blogpost.blogpostid, blogpost.posttitle, blogpost.postcontent, blogpost.publishdate, " +
                " (select count(*) from blogpostcomment where blogpostcomment.blogpostid=blogpost.blogpostid) as commentcount " +
                " from blogpost " + whereClaus + " order by blogpost.blogpostid desc";

        return template.query(sql, new RowMapper<BlogPost>() {
            @Override
            public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
                Blog blog = jdbcBlogDao.getBlogById(rs.getInt("blogid"));
                BlogPost post = new BlogPost(blog);
                post.setBlogPostId(rs.getInt("blogpostid"));
                post.setTitle(rs.getString("posttitle"));
                post.setContent(rs.getString("postcontent"));
                post.setPublishDate(rs.getTimestamp("publishdate"));
                post.setCommentCount(rs.getInt("commentcount"));
                return post;
            }
        }, parameters);
    }
    
    /**
     * Return a blog post given an id of the blog post.
     * 
     * @param blogPostId The id of the blog post
     * @return The blog post with this ID
     */
    public BlogPost getBlogPost(long blogPostId) {
        return getBlogPosts("where blogpost.blogpostid=?", blogPostId).iterator().next();
    }

    /**
     * Return a blog post given the blog and the name of the post.
     * 
     * @param blog The blog this post belongs to
     * @param postName The name of the blog post
     * @return The blog post if found
     */
    public BlogPost getBlogPost(Blog blog, String postName) {
        return getBlogPosts("where blogpost.blogid=? and blogpost.posttitle=?", blog.getId(), postName).iterator().next();
    }

}
