package com.cp.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CommentID")
    private int commentid;
    @Column(name = "Body")
    private String body;
    @Column(name = "Time")
    private String time;
    @Column(name = "time_update")
    private String time_update;

    @ManyToOne(optional = false)
    @JoinColumn(name="UserID")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name="PostID")
    private Post post;

    @OneToMany(targetEntity=CommentReply.class,mappedBy="comment",
    cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CommentReply> commentReply;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<CommentReply> getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(List<CommentReply> commentReply) {
        this.commentReply = commentReply;
    }

    
}
