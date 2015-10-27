
package org.kantega.metrics.dao;

import org.kantega.metrics.api.dao.BlogDao;
import org.kantega.metrics.api.dao.BlogPostCommentDao;
import org.kantega.metrics.api.dao.BlogPostDao;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;

import javax.sql.DataSource;

@Plugin
public class DaoPlugin {

    @Export
    private final BlogDao blogDao;

    @Export
    private final BlogPostDao blogPostDao;

    @Export
    private final BlogPostCommentDao blogPostCommentDao;

    public DaoPlugin(DataSource dataSource) {
        blogDao = new JdbcBlogDao(dataSource);
        blogPostDao = new JdbcBlogPostDao(dataSource);
        blogPostCommentDao = new JdbcBlogPostCommentDao(dataSource);
    }

}
