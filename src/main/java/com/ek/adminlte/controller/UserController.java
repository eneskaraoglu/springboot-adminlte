package com.ek.adminlte.controller;

import com.ek.adminlte.model.User;
import com.ek.adminlte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @ResponseBody
    @GetMapping("/data")
    public Map<String, Object> getUsersData(
            @RequestParam(name = "draw", required = false, defaultValue = "1") int draw,
            @RequestParam(name = "start", required = false, defaultValue = "0") int start,
            @RequestParam(name = "length", required = false, defaultValue = "10") int length,
            @RequestParam(name = "search[value]", required = false) String searchValue
    ) {
        int page = start / length;
        Page<User> usersPage;
        if (searchValue != null && !searchValue.isBlank()) {
            usersPage = userService.searchByUsernameOrEmail(searchValue, PageRequest.of(page, length));
        } else {
            usersPage = userService.findAll(PageRequest.of(page, length));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", usersPage.getTotalElements());
        response.put("recordsFiltered", usersPage.getTotalElements());
        response.put("data", usersPage.getContent());
        return response;
    }
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<User> usersPage = userService.findAll(PageRequest.of(page, size));
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("page", usersPage);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_form";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "user_form";
        } else {
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
