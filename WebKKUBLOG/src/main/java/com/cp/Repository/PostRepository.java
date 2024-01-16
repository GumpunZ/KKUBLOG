package com.cp.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.Model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer>{
    List<Post> findByCategoryCategoryid(int categoryid);
}
