package com.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.webapp.model.Post;

public class PostDaoImpl extends Dao implements PostDao {

	public PostDaoImpl(DataSource ds) {
		super(ds);
	}

	@Override
	public List<Post> getPosts() {
		List<Post> posts = dbh.query("SELECT * FROM `posts`", new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();
				
				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				
				return p;
			}
			
		});
		return posts;
	}

	@Override
	public List<Post> getPostsByAuthorId(int authorId) {
		Object[] params = {authorId};
		List<Post> posts = dbh.query("SELECT * FROM `posts` WHERE `author_id` = ?", params, new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();
				
				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				
				return p;
			}
			
		});
		
		return posts;
	}

	@Override
	public Post getPostById(int postId) {
		Object[] params = {postId};
		Post post = dbh.queryForObject("SELECT * FROM `posts` WHERE `id` = ?", params, new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();
				
				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				
				return p;
			}
			
		});
		
		return null;
	}

	@Override
	public void createPost(int authorId, String title, String content) {
		// TODO Auto-generated method stub

	}

}
