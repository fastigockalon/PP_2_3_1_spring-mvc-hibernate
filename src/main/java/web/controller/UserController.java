package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.listUsers();
        model.addAttribute("users", list);
        return "index";
    }
    @RequestMapping(value = "/addUser")
    public String addNewUser(ModelMap model) {
        User user = new User();
        model.addAttribute("add", true);
        model.addAttribute("user", user);
        return "user-edit";
    }
    @RequestMapping(value = "/saveNewUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateUser/{id}/save")
    public String saveUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id,ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("add", false);
        return "user-edit";
    }
    @RequestMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeById(id);
        return "redirect:/";
    }

}
