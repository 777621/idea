package com.lagou.dao;

import com.lagou.entity.Employee;

import java.util.List;

public interface EmployeeMapper {

    public List<Employee> findAllEmployee();
}
