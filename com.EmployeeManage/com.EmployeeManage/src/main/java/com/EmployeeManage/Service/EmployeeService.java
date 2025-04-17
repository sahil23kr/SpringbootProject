package com.EmployeeManage.Service;

import com.EmployeeManage.DAO.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService{

    Employee createNewEmployee(Employee emp);

    List<Employee> getAllEmployee();

    Optional<Employee> findById(int id);

    Optional<Employee> findByIdName(int id, String name);

    Employee updateEmpData(int id, Employee employee);

    Optional<Employee> partialEmpUpdate(int id, Map<String,Object> objectMap);
}
