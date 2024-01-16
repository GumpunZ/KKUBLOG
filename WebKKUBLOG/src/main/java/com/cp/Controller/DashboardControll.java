package com.cp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cp.Model.Comment;
import com.cp.Model.Post;
import com.cp.Model.Profile;
import com.cp.Service.UserlistService;

@Controller
public class DashboardControll {
    @Autowired
    private UserlistService userlistService;

    // หน้าหลัก
    @GetMapping("/Dashboard")
    public String displayDashboard(Model model) {
        return userlistService.displayDashboard(model);
    }

    // หน้าเเสดงโพสตัวเอง
    @GetMapping("/Dashboard/Yourpost")
    public String getPostlist(Model model) {
        return userlistService.getPostlist(model);
    }

    // หน้าแสดงคอมเม้นทั้งหมด
    @GetMapping("/Dashboard/{postid}/Youcomment/")
    public String getYouComment(@PathVariable int postid, Model model) {
        return userlistService.getYouComment(postid, model);
    }

    @GetMapping("/Dashboard/{postid}")
    public ModelAndView viewimage(@PathVariable("postid") Integer postid, Model model) {
        return userlistService.viewimage(postid, model);
    }

    @GetMapping("/Dashboard/Updateprofile/{userDetailsID}")
    public String EditProfile(@PathVariable int userDetailsID, Model model) {
         return userlistService.EditProfile(userDetailsID, model);
    }

    @PostMapping("/Dashboard/Updateprofile/{userDetailsID}")
    public String UpdateUser(@PathVariable int userDetailsID, @ModelAttribute  Profile updateprofile, Model model) {
        return userlistService.UpdateUser(userDetailsID,updateprofile,model);
    }

    @GetMapping("/Dashboard/new_post")
    public String showForm(Model model) {
        return userlistService.showForm(model);
    }

    @PostMapping("/Dashboard/new_post")
    public String addpost(@Validated Post post, @RequestParam("imageFile") MultipartFile imageFile,
            BindingResult result, Model model)
            throws IOException, SerialException, SQLException {
        return userlistService.addpost(post, imageFile, result, model);
    }

    @GetMapping("/Dashboard/edit_post/{postid}")
    public String showUpdatePost(@PathVariable int postid, Model model) {
        return userlistService.showUpdatePost(postid, model);
    }

    @GetMapping("/Dashboard/{postid}/edit_post/delete_image/{imageid}")
    public String deleteImage(@PathVariable("imageid") Integer imageid, @PathVariable int postid, Model model) {
        return userlistService.deleteImage(imageid, postid, model);
    }

    @PostMapping("/Dashboard/edit_post/{postid}")
    public String updatePost(@PathVariable int postid, @ModelAttribute Post post,
            @RequestParam("imageFile") MultipartFile imageFile, BindingResult result, Model model)
            throws IOException, SerialException, SQLException {
        return userlistService.updatePost(postid, post, imageFile, result, model);
    }

    @GetMapping("/Dashboard/delete_post/{postid}")
    public String deletePost(@PathVariable("postid") Integer postid, Model model) {
        return userlistService.deletePost(postid, model);
    }

    @GetMapping("/Dashboard/{postid}/Comment")
    public String showFormComment(@PathVariable("postid") Integer postid, Model model) {
        return userlistService.showFormComment(postid, model);
    }

    @PostMapping("/Dashboard/{postid}/Comment")
    public String getComment(@Validated Comment comment, @PathVariable int postid,
            BindingResult result, Model model) throws IOException, SerialException, SQLException {
        return userlistService.getComment(comment, postid, result, model);
    }

    @GetMapping("/Dashboard/{postid}/delete_comment/{commentid}")
    public String deleteComment(@PathVariable("postid") Integer postid, @PathVariable("commentid") Integer commentid,
            Model model) {
        return userlistService.deleteComment(postid, commentid, model);
    }

    @GetMapping("/Dashboard/{postid}/edit_comment/{commentid}")
    public String showUpdateComment(@PathVariable int commentid, @PathVariable int postid, Model model) {
        return userlistService.showUpdateComment(commentid, postid, model);
    }

    @PostMapping("/Dashboard/{postid}/edit_comment/{commentid}")
    public String updateComment(@PathVariable int postid, @PathVariable int commentid, @ModelAttribute Comment comment,
            Model model) {
        return userlistService.updateComment(postid, commentid, comment, model);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("image_post") int image_post)
            throws IOException, SQLException {
        return userlistService.displayImage(image_post);
    }

}
