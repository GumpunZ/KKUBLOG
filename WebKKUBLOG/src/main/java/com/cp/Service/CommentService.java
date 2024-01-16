package com.cp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.Model.Comment;
import com.cp.Repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAllComment(){
        List<Comment> commentlist = new ArrayList<>();
        commentRepository.findAll().forEach(commentlist::add);
        return commentlist;
    }

    public void saveComment(Comment c){
        this.commentRepository.save(c);
    }

    public Comment getCommentId(Integer commentid){
        return this.commentRepository.findById(commentid).get();
    }

    public void deleteComment(Comment c){
        this.commentRepository.delete(c);
    }

    public List<Comment> getCommentsByPostId(int postid) {
        return commentRepository.findByPostPostid(postid);
    }
}
