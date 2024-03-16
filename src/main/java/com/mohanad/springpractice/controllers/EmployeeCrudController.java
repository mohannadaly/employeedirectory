package com.mohanad.springpractice.controllers;

import com.mohanad.springpractice.entities.Employee;
import com.mohanad.springpractice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employees")
public class EmployeeCrudController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeCrudController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("create")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-create";
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees-list";
    }

    @GetMapping("update")
    public String showUpdateEmployeeForm(
            @RequestParam Integer id,
            Model model
    ) {
        model.addAttribute("employee", employeeService.findById(id).getFirst());
        return "employee-update";
    }

    @PostMapping
    public String createEmployee(
            @Valid @ModelAttribute("employee") Employee newEmployee,
            BindingResult bindingResult
    ) {
        if (!bindingResult.hasErrors()) {
            employeeService.create(newEmployee);
        }
        return "redirect:/employees";
    }

    @PutMapping
    public String updateEmployee(
            @RequestParam Integer id,
            @Valid @ModelAttribute("employee") Employee newEmployee,
            BindingResult bindingResult
    ) {
        if (!bindingResult.hasErrors()) {
            employeeService.update(id, newEmployee);
        }
        return "redirect:/employees";
    }

    @GetMapping("delete")
    public String deleteEmployee(@RequestParam Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
