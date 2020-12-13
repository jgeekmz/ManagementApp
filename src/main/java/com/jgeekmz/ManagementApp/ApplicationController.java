package com.jgeekmz.ManagementApp;

import com.jgeekmz.ManagementApp.models.Post;
import com.jgeekmz.ManagementApp.repositories.*;
import com.jgeekmz.ManagementApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {
    private final VehicleRepository vehicleRepo;
    private final EmployeeRepository employeeRepo;
    private final LocationRepository locationRepo;
    private final PostService postService;
    private final PostRepository postRepo;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationController(VehicleRepository vehicleRepo, EmployeeRepository employeeRepo, LocationRepository locationRepo, PostService postService, PostRepository postRepo, UserRepository userRepository) {
        this.vehicleRepo = vehicleRepo;
        this.employeeRepo = employeeRepo;
        this.locationRepo = locationRepo;
        this.postService = postService;
        this.postRepo = postRepo;
        this.userRepository = userRepository;
    }

    //Index page plus count vehicles, users, active users, employees, locations. Find all posts.
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String goHome(Model model, Long id) {
        model.addAttribute("tcount", vehicleRepo.count());
        model.addAttribute("empcount", employeeRepo.count());
        model.addAttribute("locations", locationRepo.count());
        model.addAttribute("posts", postRepo.findAll());
        model.addAttribute("tusers", userRepository.count());

        // count of not active users
        model.addAttribute("tactiveUsers", userRepository.countByID(id));
        return "index";
    }

    @RequestMapping("/")
    public String goDashboard() {
        return "/index";
    }

    //Add new post on the index page
    @RequestMapping(value = "/index/addNewPost", method = RequestMethod.POST)
    public String addNewPost(Post post) {
        System.out.println(post);
        postService.save(post);
        return "redirect:/index";
    }

/*  //Going to home page
    @GetMapping("/index")
    public String goHome () { return "index"; }*/

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/blank")
    public String getBlank() {
        return "blank";
    }
}