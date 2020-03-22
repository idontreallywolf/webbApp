package com.webapp.dao;

import java.util.List;

import com.webapp.model.Post;

public interface PostDao {
    public List<Post> getPosts();
    public List<Post> getPostsByAuthorId(int authorId);
    public Post getPostById(int postId);
    public void    createPost(int authorId, String title,  String content);
}
