package com.cp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.cp.Model.Comment;
import com.cp.Model.Image;
import com.cp.Model.Post;
import com.cp.Model.User;
import com.cp.Service.CommentService;
import com.cp.Service.PostService;

@Controller
public class GuestController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/Dashboard/Guest")
    public String DashboardGuest(Model model) {
        List<Post> category1 = postService.findAllPostsByCategoryId(1);
        model.addAttribute("category1", category1);
        List<Post> category2 = postService.findAllPostsByCategoryId(2);
        model.addAttribute("category2", category2);
        List<Post> category3 = postService.findAllPostsByCategoryId(3);
        model.addAttribute("category3", category3);
        List<Post> category4 = postService.findAllPostsByCategoryId(4);
        model.addAttribute("category4", category4);
        List<Post> category5 = postService.findAllPostsByCategoryId(5);
        model.addAttribute("category5", category5);
        List<Post> category6 = postService.findAllPostsByCategoryId(6);
        model.addAttribute("category6", category6);
        return "Guest";
    }

    @GetMapping("/Dashboard/{postid}/Guest")
    public ModelAndView viewimage(@PathVariable("postid") Integer postid, Model model) {
        ModelAndView mv = new ModelAndView("LookpostGuest");
        Post post = postService.getPostId(postid);
        model.addAttribute("lookpost", post);
        Image image = post.getImage();
        mv.addObject("Imageview", image);
        User userpost = post.getUser();
        model.addAttribute("postUser", userpost);
        List<Comment> comments = commentService.getCommentsByPostId(postid);
        model.addAttribute("Comment", comments);
        return mv;
    }
}
