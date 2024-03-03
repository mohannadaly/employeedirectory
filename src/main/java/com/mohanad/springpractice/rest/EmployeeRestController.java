package com.mohanad.springpractice.rest;

import com.mohanad.springpractice.entities.Employee;
import com.mohanad.springpractice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> find(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String firstName) {
        if (id != null)
            return employeeService.findById(id);
        if (firstName != null)
            return employeeService.findByFirstName(firstName);
        return employeeService.findAll();
    }

    @PutMapping("{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }

}
