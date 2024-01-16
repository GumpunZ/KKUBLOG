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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PostID", unique = true, nullable = false)
    private int postid;
    @Column(name = "Title")
    private String title;
    @Column(name = "Tags")
    private String tags;
    @Column(name = "Time")
    private String time;
    @Column(name = "Body")
    private String body;
    @Column(name = "update_post")
    private String update_post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment;

    @OneToOne(mappedBy = "post",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Image image;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUpdate_post() {
        return update_post;
    }

    public void setUpdate_post(String update_post) {
        this.update_post = update_post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    

}
