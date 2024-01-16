package com.cp.Service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cp.Model.Category;
import com.cp.Model.Comment;
import com.cp.Model.Image;
import com.cp.Model.Post;
import com.cp.Model.Profile;
import com.cp.Model.User;
import com.cp.Repository.PostRepository;
import com.cp.Repository.ProfileRepository;
import com.cp.Repository.UserRepository;

@Service
public class UserlistService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;

    // การใช้งาน User
    public List<User> findAllUser() {
        List<User> userlist = new ArrayList<>();
        userRepository.findAll().forEach(userlist::add);
        return userlist;
    }

    public User findUser(Integer userid) {
        return this.userRepository.findById(userid).get();
    }

    public String EditProfile(@PathVariable int userDetailsID, Model model) {
        Profile profileupdate = profileRepository.findById(userDetailsID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
        model.addAttribute("updateprofile", profileupdate);
        return "Updateprofile";
    }

    public String UpdateUser(@PathVariable int userDetailsID, @ModelAttribute Profile updateprofile, Model Model) {
        User userupdate = userRepository.findById(userDetailsID);
        Profile profileUpdate = userupdate.getProfile();
        profileUpdate.setName(updateprofile.getName());
        profileUpdate.setEmail(updateprofile.getEmail());
        profileUpdate.setPhone(updateprofile.getPhone());
        profileUpdate.setDate_of_birth(updateprofile.getDate_of_birth());
        profileRepository.save(profileUpdate);
        return "redirect:/Dashboard";
    }

    // แสดงหน้าหลัก
    @GetMapping("/Dashboard")
    public String displayDashboard(Model model) {
        String user = returnUsername();
        model.addAttribute("userDetails", user);
        Integer userid = returnUsernameID();
        Profile profile = profileRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("Profile not found for user with ID " + userid));
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
        return "Dashboard";
    }

    //แสดงหน้าโพสตัวเอง
    public String getPostlist(Model model) {
        String username = returnUsername();
        Integer userid = returnUsernameID();
        User user = getUserById(userid);
        List<Post> postList = postService.findAllPost();
        List<Post> userpost = new ArrayList<>();
        for (Post post : postList) {
            if (post.getUser() != null && post.getUser().equals(user))
                userpost.add(post);
        }

        model.addAttribute("Postlist", userpost);
        model.addAttribute("userDetails", username);
        return "Yourpost";
    }

    //แสดงหน้า Comment
    public String getYouComment(@PathVariable int postid, Model model) {
        Post post = postService.getPostId(postid);
        model.addAttribute("lookpost", post);
        Integer userid = returnUsernameID();
        User user = getUserById(userid);
        List<Comment> commentlist = commentService.getCommentsByPostId(postid);
        List<Comment> commentuser = new ArrayList<>();
        for (Comment comment : commentlist) {
            if (comment.getUser() != null && comment.getUser().equals(user))
                commentuser.add(comment);
        }
        model.addAttribute("Commentlist", commentuser);
        return "Yourcomment";
    }

    // การใช้งาน Post
    public String showForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        List<Category> categorylist = (List<Category>) postService.findallCategory();
        model.addAttribute("categorylist", categorylist);
        return "Post";
    }

    public String addpost(@Validated Post post, @RequestParam("imageFile") MultipartFile imageFile,
            BindingResult result, Model model) throws IOException, SerialException, SQLException {
        if (result.hasErrors()) {
            List<Category> categorylist = (List<Category>) postService.findallCategory();
            model.addAttribute("categorylist", categorylist);
            Integer userid = returnUsernameID();
            model.addAttribute("userid", userid);
            return "Post";
        }
        Integer userid = returnUsernameID();
        User user = getUserById(userid);
        post.setUser(user);
        postService.savePost(post);

        Image image = new Image();
        Blob blobImage = new SerialBlob(imageFile.getBytes());
        image.setImage(blobImage);
        image.setPost(post);
        imageService.saveImage(image);
        return "redirect:/Dashboard";
    }

    public String showUpdatePost(@PathVariable int postid, Model model) {
        Post postupdate = postRepository.findById(postid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID" + postid));
        model.addAttribute("post", postupdate);
        List<Category> categorylist = (List<Category>) postService.findallCategory();
        model.addAttribute("categorylist", categorylist);
        return "Updatepost";
    }

    public String updatePost(@PathVariable int postid, @ModelAttribute Post post,
            @RequestParam("imageFile") MultipartFile imageFile, BindingResult result, Model model)
            throws IOException, SerialException, SQLException {
        Post postup = postService.getPostId(postid);
        if (result.hasErrors()) {
            post.setPostid(postid);
            return "Updatepost";
        }
        postup.setTitle(post.getTitle());
        postup.setBody(post.getBody());
        postup.setTags(post.getTags());
        postup.setUpdate_post(post.getUpdate_post());
        postup.setCategory(post.getCategory());
        postRepository.save(postup);

        if (!imageFile.isEmpty()) {
            try {
                Blob blobImage = new SerialBlob(imageFile.getBytes());
                Image images = postup.getImage();
                if (images == null) {
                    images = new Image();
                    images.setPost(postup);
                }
                images.setImage(blobImage);
                imageService.saveImage(images);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/Dashboard/Yourpost";
    }

    public String deletePost(@PathVariable("postid") Integer postid, Model model) {
        Post post = postService.getPostId(postid);
        postService.deletePost(post);
        return "redirect:/Dashboard/Yourpost";
    }

    // Image

    public ResponseEntity<byte[]> displayImage(@RequestParam("image_post") int image_post)
            throws IOException, SQLException {
        Image image = imageService.getImageId(image_post);
        byte[] imageBytes = null;
        imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // LookPost
    public ModelAndView viewimage(@PathVariable("postid") Integer postid, Model model) {
        ModelAndView mv = new ModelAndView("Lookpost");
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

    public String deleteImage(@PathVariable("imageid") Integer imageid, @PathVariable int postid, Model model) {
        Image image = imageService.getImageId(imageid);
        imageService.deleteImage(image);
        return "redirect:/Dashboard/edit_post/{postid}";
    }

    // Comment

    public String showFormComment(@PathVariable Integer postid, Model model) {
        Post postcommentid = postRepository.findById(postid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID" + postid));
        model.addAttribute("post", postcommentid);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "Comment";
    }

    public String getComment(@Validated Comment comment, @PathVariable int postid,
            BindingResult result, Model model) throws IOException, SerialException, SQLException {
        if (result.hasErrors()) {
            Integer userid = returnUsernameID();
            model.addAttribute("userid", userid);
            return null;
        }
        Integer userid = returnUsernameID();
        Post post = postService.getPostId(postid);
        User user = getUserById(userid);
        comment.setPost(post);
        comment.setUser(user);
        commentService.saveComment(comment);
        return "redirect:/Dashboard/{postid}";
    }

    public String showUpdateComment(@PathVariable int commentid, @PathVariable int postid, Model model) {
        Comment commentupdate = commentService.getCommentId(commentid);
        model.addAttribute("commentupdate", commentupdate);
        Post post = postService.getPostId(postid);
        model.addAttribute("postcommentupdate", post);
        return "Updatecomment";
    }

    public String updateComment(@PathVariable int postid, @PathVariable int commentid, @ModelAttribute Comment comment,
            Model model) {
        Comment getcommentid = commentService.getCommentId(commentid);
        getcommentid.setBody(comment.getBody());
        getcommentid.setTime_update(comment.getTime_update());
        commentService.saveComment(getcommentid);
        return "redirect:/Dashboard/{postid}/Youcomment/";
    }

    public String deleteComment(@PathVariable("postid") Integer postid, @PathVariable("comment") Integer commentid,
            Model model) {
        Comment comment = commentService.getCommentId(commentid);
        commentService.deleteComment(comment);
        return "redirect:/Dashboard/{postid}/Youcomment/";
    }

    // การรีเทิน Username เเละ id
    public String returnUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        User users = userRepository.findByUsername(user.getUsername());
        return users.getUsername();
    }

    public User getUserById(int userid) {
        return userRepository.findById(userid);
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
