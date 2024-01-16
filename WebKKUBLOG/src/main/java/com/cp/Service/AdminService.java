package com.cp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.cp.Model.Post;
import com.cp.Model.Profile;
import com.cp.Model.User;
import com.cp.Repository.PostRepository;
import com.cp.Repository.ProfileRepository;
import com.cp.Repository.UserRepository;

@Service
public class AdminService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserlistService userlistService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PostService postService;

    public String showUser(Model model){
       List<User> userlist = (ArrayList<User>) userlistService.findAllUser();
        model.addAttribute("usershow", userlist);
        return "Admin"; 
    }

    public String displayDashboard(Model model){
        String username = returnUsername();
        model.addAttribute("username", username);
        List<User> userlist = (ArrayList<User>) userlistService.findAllUser();
        model.addAttribute("usershow", userlist);
        List<Post> postlist = (ArrayList<Post>) postService.findAllPost();
        model.addAttribute("postshow", postlist);

        String user = userlistService.returnUsername();
        model.addAttribute("userDetails", user);
        Integer userid = userlistService.returnUsernameID();
        Profile profile = profileRepository.getById(userid);
        model.addAttribute("profile", profile);

        /*
         * List<Post> postlist = (ArrayList<Post>) postService.findAllPost();
         * model.addAttribute("postshow", postlist);
         */

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
        return "Admin";
    }

    public String DeleteUser(@PathVariable int userid,Model model){
        userRepository.deleteById(userid);
        List<User> deleteuser = (ArrayList<User>) userlistService.findAllUser();
        model.addAttribute("deleteuser", deleteuser);
        String username = returnUsername();
        model.addAttribute("username", username);
        List<User> userlist = (ArrayList<User>) userlistService.findAllUser();
        model.addAttribute("usershow", userlist);
        List<Post> postlist = (ArrayList<Post>) postService.findAllPost();
        model.addAttribute("postshow", postlist);
        return "redirect:/Admin";
    }

    public String DeletePost(@PathVariable int PostID,Model model){
        postRepository.deleteById(PostID);
        List<Post> deletepost = (ArrayList<Post>) postService.findAllPost();
        model.addAttribute("deletepost", deletepost);
        String username = returnUsername();
        model.addAttribute("username", username);
        List<User> userlist = (ArrayList<User>) userlistService.findAllUser();
        model.addAttribute("usershow", userlist);
        List<Post> postlist = (ArrayList<Post>) postService.findAllPost();
        model.addAttribute("postshow", postlist);
        return "redirect:/Admin";
    }

    public String returnUsername(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByUsername(user.getUsername());
        return users.getUsername();
    }

    public int returnUsernameID() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
        if (userDetails instanceof UserServiceID) {
            UserServiceID userDetailsWithId = (UserServiceID) userDetails;
            int userId = userDetailsWithId.getId();
            return userId;
        }
        return -1;
    }
}
