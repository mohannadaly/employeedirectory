package com.mohanad.springpractice.service;

import com.mohanad.springpractice.dao.EmployeeRepository;
import com.mohanad.springpractice.entities.Employee;
import com.mohanad.springpractice.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(0);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) return List.of(employee.get());
        throw new EmployeeNotFoundException("Employee with id: " + id + " not found.");
    }

    @Override
    public List<Employee> findByFirstName(String name) {
        return employeeRepository.findByFirstName(name);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Integer id, Employee employee) {
        findById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }


    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
