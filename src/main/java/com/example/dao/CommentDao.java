package com.example.dao;

import com.example.model.Comment;
import java.util.List;

public interface CommentDao {
    void saveComment(Comment comment);
    Comment getCommentById(long id);
    List<Comment> getCommentsByTaskId(long taskId);
    void updateComment(Comment comment);
    void deleteComment(long id);
}