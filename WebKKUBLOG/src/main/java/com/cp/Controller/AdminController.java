package com.cp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cp.Service.AdminService;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/Admin")
    public String displayDashboard(Model model){
        return adminService.displayDashboard(model);
    }

    @GetMapping("/Admin/DeleteUser/{userid}")
    public String DeleteUser(@PathVariable int userid,Model model){
        return adminService.DeleteUser(userid, model);
    }

    @GetMapping("/Admin/DeletePost/{postid}")
    public String DeletePost(@PathVariable int postid,Model model){
        return adminService.DeletePost(postid, model);
    }
    
}
