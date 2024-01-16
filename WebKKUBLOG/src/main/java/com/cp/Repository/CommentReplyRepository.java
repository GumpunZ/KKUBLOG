package com.cp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.CommentReply;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReply,Integer>{  
}
