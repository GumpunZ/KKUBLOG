package com.cp.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.Model.Category;
import com.cp.Model.Post;
import com.cp.Repository.CategoryRepository;
import com.cp.Repository.PostRepository;
import com.cp.Repository.UserRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Post> findAllPost(){
        List<Post> postlist = new ArrayList<>();
        postRepository.findAll().forEach(postlist::add);
        return postlist;
    }
    public void savePost(Post p){
        this.postRepository.save(p);
    }

    public Post getPostId(Integer postid){
        return this.postRepository.findById(postid).get();
    }
    public void deletePost(Post p){
        this.postRepository.delete(p);
    }
    
    public List<Category> findallCategory(){
        List<Category> catlist = new ArrayList<>();
        categoryRepository.findAll().forEach(catlist::add);
        return catlist;
    }

    public List<Post> findAllPostsByCategoryId(int categoryId) {
        return postRepository.findByCategoryCategoryid(categoryId);
    }
}
