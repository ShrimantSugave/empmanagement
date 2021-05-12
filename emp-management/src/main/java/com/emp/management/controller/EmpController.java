package com.emp.management.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.management.dto.EmployeeDto;
import com.emp.management.dto.GetCityWiseEmployeeCount;
import com.emp.management.dto.GetDepartmentWiseEmployeeCount;
import com.emp.management.model.Response;
import com.emp.management.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/emp")
public class EmpController
{
	@Autowired
	EmployeeServiceImpl empService;
	
	Logger log4j = LogManager.getLogger(EmpController.class);
	
	
	@PostMapping("/register-emp")
	public Response<String> registerEmp(@RequestBody EmployeeDto employeeDto)
	{
		Response empResponse = empService.registerEmployee(employeeDto);
		return empResponse;
	}
	
	@PostMapping("/update-emp")
	public Response<String> updateEmp(@RequestBody EmployeeDto employeeDto)
	{
		Response empResponse = empService.updateEmployee(employeeDto);
		return empResponse;
	}
	
	@PostMapping("/department-wise-count-of-employees")
	public Response<String> departmentWiseCountOfEmployees(@RequestBody GetDepartmentWiseEmployeeCount getDepartmentWiseEmployeeCount)
	{
		Response empResponse = empService.departmentWiseCountOfEmployees(getDepartmentWiseEmployeeCount);
		return empResponse;
	}
	
	@PostMapping("/employees-belongs-to-particular-city")
	public Response<String> employeesBelongsToParticularCity(@RequestBody GetCityWiseEmployeeCount getCityWiseEmployeeCount)
	{
		Response empResponse = empService.employeesBelongsToParticularCity(getCityWiseEmployeeCount);
		return empResponse;
	}
}
