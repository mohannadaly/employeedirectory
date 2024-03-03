package com.mohanad.springpractice.service;

import com.mohanad.springpractice.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    List<Employee> findById(Integer id);

    List<Employee> findByFirstName(String name);

    List<Employee> findAll();

    Employee update(Integer id, Employee employee);

    void delete(Integer id);

}
