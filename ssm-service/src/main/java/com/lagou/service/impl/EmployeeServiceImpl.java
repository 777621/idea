package com.lagou.service.impl;

import com.lagou.dao.EmployeeMapper;
import com.lagou.entity.Employee;
import com.lagou.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAllEmployee() {

        List<Employee> employeeList = employeeMapper.findAllEmployee();

        return employeeList;
    }
}
