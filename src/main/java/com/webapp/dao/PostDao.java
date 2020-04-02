package com.webapp.dao;

import java.util.List;

import com.webapp.enums.Category;
import com.webapp.model.Post;

public interface PostDao {
    public List<Post> getPosts();
    public List<Post> getPostsByAuthorId(int authorId);
    public List<Post> getPostsByCategory(Category categoryType);
    public Post getPostById(int postId);
    public boolean createPost(int authorId, String title,  String imgName, int category);
}
