package com.example.service;

import com.example.dao.CommentDao;
import com.example.model.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	  public void saveComment(Comment comment) {
		  commentDao.saveComment(comment);

	}

    public void addComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    public List<Comment> getCommentsByTaskId(int taskId) {
        return commentDao.getCommentsByTaskId(taskId);
    }

    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }
}