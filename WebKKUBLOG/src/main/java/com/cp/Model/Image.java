package com.cp.Model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_post", unique = true, nullable = false)
    private int image_post;
    
    @Lob
    @Column(name = "image")
    private Blob image;

    @OneToOne
    @MapsId
    @JoinColumn(name="image_post")
    private Post post;

    public int getImage_post() {
        return image_post;
    }

    public void setImage_post(int image_post) {
        this.image_post = image_post;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
