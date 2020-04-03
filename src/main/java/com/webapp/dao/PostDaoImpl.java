package com.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.webapp.Config;
import com.webapp.enums.Category;
import com.webapp.model.Post;

public class PostDaoImpl extends Dao implements PostDao {

	private HashMap<Integer, String> emotes;

	public PostDaoImpl(DataSource ds) {
		super(ds);

		emotes = new HashMap<Integer, String>();
		emotes.put(0, "&#127808;");
		emotes.put(1, "&#128054;");
		emotes.put(2, "&#128514;");
		emotes.put(3, "&#129313;");
		emotes.put(4, "&#10067;");
	}

	@Override
	public List<Post> getPosts() {
		List<Post> posts = dbh.query("SELECT * FROM `posts` ORDER BY `time` DESC", new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();

				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setCategory(rs.getInt("category"));
				p.setEmote(emotes.get(rs.getInt("category")));

				return p;
			}

		});
		return posts;
	}

	@Override
	public List<Post> getPostsByAuthorId(int authorId) {
		Object[] params = {authorId};
		List<Post> posts = dbh.query("SELECT * FROM `posts` WHERE `author_id` = ? ORDER BY `time` DESC", params, new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();

				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setCategory(rs.getInt("category"));
				p.setEmote(emotes.get(rs.getInt("category")));

				return p;
			}

		});

		return posts;
	}

	@Override
	public List<Post> getPostsByCategory(int category) {
		String qryStr;
		Object[] params = {category};
		
		if (category > Config.Category.CATEGORY_MIN 
		&&  category <= Config.Category.CATEGORY_MAX) 
			qryStr = "SELECT * FROM `posts` WHERE `category` = ? ORDER BY `time` DESC";
		else
			return getPosts();

		List<Post> posts = dbh.query(qryStr, params, new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();

				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setCategory(rs.getInt("category"));
				p.setEmote(emotes.get(rs.getInt("category")));

				return p;
			}

		});

		return posts;
	}

	@Override
	public Post getPostById(int postId) {
		Object[] params = {postId};
		Post post = dbh.queryForObject("SELECT * FROM `posts` WHERE `id` = ? ORDER BY `time` DESC", params, new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post p = new Post();

				p.setId(rs.getInt("id"));
				p.setAuthorId(rs.getInt("author_id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setCategory(rs.getInt("category"));
				p.setEmote(emotes.get(rs.getInt("category")));

				return p;
			}

		});

		return post;
	}

	@Override
	public boolean createPost(int authorId, String title, String imgName, int category) {
		String sqlInsertQuery = "INSERT INTO `posts`(`author_id`,`title`,`content`,`category`) VALUES(?, ?, ?, ?)";
		try {
			dbh.update(sqlInsertQuery, authorId, title, imgName, category);
		} catch(DataAccessException e) {
			return false;
		}

		return true;
	}

}
