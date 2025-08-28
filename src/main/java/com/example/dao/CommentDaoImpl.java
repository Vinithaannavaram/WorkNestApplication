package com.example.dao;

import com.example.model.Comment;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Override
    public void saveComment(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(comment);
        tx.commit();
        session.close();
    }

    @Override
    public Comment getCommentById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Comment comment = session.get(Comment.class, id);
        session.close();
        return comment;
    }

    @Override
    public List<Comment> getCommentsByTaskId(long taskId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> comments = session.createQuery("FROM Comment WHERE taskId = :taskId", Comment.class)
                                        .setParameter("taskId", taskId)
                                        .list();
        session.close();
        return comments;
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(comment);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteComment(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Comment comment = session.get(Comment.class, id);
        if (comment != null) {
            session.delete(comment);
        }
        tx.commit();
        session.close();
    }

}