package com.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "task_id", nullable = false)

    private Long taskId;

    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // ✅ Constructors
    public Comment() {}

    public Comment(Long taskId, String text, String authorName, LocalDateTime timestamp) {
        this.taskId = taskId;
        this.text = text;
        this.authorName = authorName;
        this.timestamp = timestamp;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", text='" + text + '\'' +
                ", authorName='" + authorName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}