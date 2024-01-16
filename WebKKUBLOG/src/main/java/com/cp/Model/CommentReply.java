package com.cp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commentreply")
public class CommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CommentReplyID")
    private int commentreplyid;
    @Column(name = "Body")
    private String body;
    @Column(name = "Time")
    private String time;
    @Column(name = "time_update")
    private String time_update;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CommentID")
    private Comment comment;

    public int getCommentreplyid() {
        return commentreplyid;
    }

    public void setCommentreplyid(int commentreplyid) {
        this.commentreplyid = commentreplyid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime_update() {
        return time_update;
    }

    public void setTime_update(String time_update) {
        this.time_update = time_update;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    
}
