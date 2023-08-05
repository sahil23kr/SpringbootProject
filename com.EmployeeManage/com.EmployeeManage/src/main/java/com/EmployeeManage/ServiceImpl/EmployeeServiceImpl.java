package com.EmployeeManage.ServiceImpl;

import com.EmployeeManage.DAO.Employee;
import com.EmployeeManage.Exception.EmpIdNameNotFoundException;
import com.EmployeeManage.Exception.EmployeeIdNotFoundException;
import com.EmployeeManage.Repo.EmployeeRepo;
import com.EmployeeManage.Service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

   @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public Employee createNewEmployee(Employee emp) {
        //EmployeeDTO employeeDTO=new EmployeeDTO();
        Employee empdata=new Employee();
        empdata.setEmp_Name(emp.getEmp_Name());
        empdata.setEmp_Age(emp.getEmp_Age());
        empdata.setEmp_Email(emp.getEmp_Email());
        empdata.setEmp_Password(emp.getEmp_Password());
        empdata.setEmp_Salary(emp.getEmp_Salary());
        employeeRepo.save(empdata);
        return empdata;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList=employeeRepo.findAll();
        return employeeList;
    }

    @Override
    public Optional<Employee> findById(int id) {
        Optional<Employee> employee=employeeRepo.findById(id);
        if (employee.isEmpty())
            throw new EmployeeIdNotFoundException();
        return employee ;
    }

    @Override
    public Optional<Employee> findByIdName(int id, String name) {
        Optional<Employee> employee=employeeRepo.findByIdName(id,name);
        if (employee.isEmpty())
            throw new EmpIdNameNotFoundException();
        return employee;
    }

    @Override
    public Employee updateEmpData(int id, Employee employee) {
        Employee employee1=new Employee();
        Optional<Employee> id1=employeeRepo.findById(id);
        if (id1.isEmpty())
            throw new EmployeeIdNotFoundException();
        employee1.setEmp_Name(employee.getEmp_Name());
        employee1.setEmp_Salary(employee.getEmp_Salary());
        employee1.setEmp_Age(employee.getEmp_Age());
        employee1.setEmp_Password(employee.getEmp_Password());
        employee1.setEmp_Email(employee.getEmp_Email());
        employeeRepo.save(employee1);
        return employee1;
    }

    @Override
    public Optional<Employee> partialEmpUpdate(int id, Map<String,Object> employee) {
        //Employee employee1=new Employee();
        Optional<Employee> employee1=employeeRepo.findById(id);
        if (employee1.isEmpty())
            throw new EmployeeIdNotFoundException();
        /*employee1.setEmp_Name(employee.getEmp_Name());
        employee1.setEmp_Salary(employee.getEmp_Salary());
        employee1.setEmp_Age(employee.getEmp_Age());
        employee1.setEmp_Password(employee.getEmp_Password());
        employee1.setEmp_Email(employee.getEmp_Email());*/
        employee.forEach((k,v)->{
            System.out.println(k+" "+v);
            Field field= ReflectionUtils.findField(Employee.class,k);
            field.setAccessible(true);
            ReflectionUtils.setField(field,employee1.get(),v);
        });
        employeeRepo.save(employee1.get());
        return employee1;
    }

}
