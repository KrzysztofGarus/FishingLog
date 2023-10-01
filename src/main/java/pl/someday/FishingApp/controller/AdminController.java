package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.user.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminController(UserRepository userRepository) {
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(){
        return "/admin/dashboard";
    }

}


