package com.mohanad.springpractice.dao;

import com.mohanad.springpractice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstName(String firstName);

    List<Employee> findAllByOrderByFirstNameAsc();
}
