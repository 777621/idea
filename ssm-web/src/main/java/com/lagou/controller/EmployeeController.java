package com.lagou.controller;

import com.lagou.entity.Employee;
import com.lagou.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController    //代替@RestController + @ResponseBody
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/findAll")
    public List<Employee> findAll(){

        List<Employee> employeeList = employeeService.findAllEmployee();

        return employeeList;

    }
}
