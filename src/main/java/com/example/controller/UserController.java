package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.service.UserService;
import com.example.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Daftar Pengguna
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    User user;
    
    // Formulir Tambah atau Edit Pengguna
    @GetMapping("/form")
    public String formUser(@RequestParam(value = "user", required = false) String User, Model model) {
        model.addAttribute("username", new Object());
        model.addAttribute("email", new Object());
        model.addAttribute("password", new Object());
        model.addAttribute("role", new Object());
        user = (user != null) ? userService.findByName(User) : new User();
        model.addAttribute("user", user);
        return "users/form"; // Mengarahkan ke templates/users/form.html
    }
    
    // Simpan Pengguna
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // Hapus Pengguna
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable ("id") String id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
