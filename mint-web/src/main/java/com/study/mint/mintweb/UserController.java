package com.study.mint.mintweb;
import com.study.mint.mintweb.User;
import com.study.mint.mintweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // home page (view all users) - GET -> GET
    @GetMapping("/")
    public String viewAll(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }

    //create new user - GET -> GET
    @GetMapping("/createForm")
    public String createForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    //save user - POST -> POST
    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    //update user - PUT -> GET
    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }

    //delete User - DELETE -> GET
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {

        this.userService.deleteUserById(id);
        return "redirect:/";
    }


}
