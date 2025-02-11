package com.cp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Integer>{
    List<Comment> findByPostPostid(int postid);
}
