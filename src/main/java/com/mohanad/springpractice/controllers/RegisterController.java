package com.mohanad.springpractice.controllers;

import com.mohanad.springpractice.entities.Employee;
import com.mohanad.springpractice.entities.User;
import com.mohanad.springpractice.entities.WebUser;
import com.mohanad.springpractice.service.EmployeeService;
import com.mohanad.springpractice.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("register")
public class RegisterController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @Autowired
    public RegisterController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("newUser", new WebUser());
        return "register/form";
    }

    @PostMapping
    public String processRegistrationForm(@Valid @ModelAttribute("newUser") WebUser newUser, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "register/form";
        }
        User existingUser = userService.findByUserName(newUser.getUserName());
        if (existingUser != null) {
            model.addAttribute("newUser", new WebUser());
            model.addAttribute("registrationError", "Username already exists.");
            return "register/form";
        }
        List<Employee> existingEmployees = employeeService.findById(newUser.getId());
        if (existingEmployees.isEmpty()) {
            model.addAttribute("newUser", new WebUser());
            model.addAttribute("registrationError", "Invalid employee id.");
            return "register/form";
        } else {
            existingEmployees = employeeService.findById(newUser.getId());
            if (existingEmployees.isEmpty()) {
                model.addAttribute("newUser", new WebUser());
                model.addAttribute("registrationError", "Employee ID doesn't match.");
                return "register/form";
            }
        }

        userService.save(newUser);
        httpSession.setAttribute("user", newUser);

        return "register/confirmation";
    }
}
