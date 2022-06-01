package com.employee;

import com.employee.dto.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class Controller {

    @Autowired
    FetchData fetchData;

    @Autowired
    EmployeeService employee;

    @GetMapping
    APIResponse<List<UserModel>>getEmployee() {
        List<UserModel> allEmployee = employee.findAllEmployees();
        return new APIResponse<>(allEmployee.size(), allEmployee);
    }

    @GetMapping("/{field}")
    APIResponse<List<UserModel>> getEmployeesWithSort(@PathVariable String field) {
        List<UserModel> allEmployee = employee.findEmployeesSorting(field);
        return new APIResponse<>(allEmployee.size(), allEmployee);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    APIResponse<Page<UserModel>> getEmployeesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<UserModel> allPage = employee.employeeWithPagination(offset,pageSize);
        return new APIResponse<>(allPage.getSize(), allPage);
    }
}