package com.emp.management.service;

import com.emp.management.dto.EmployeeDto;
import com.emp.management.dto.GetCityWiseEmployeeCount;
import com.emp.management.dto.GetDepartmentWiseEmployeeCount;
import com.emp.management.model.Response;

public interface EmployeeService
{
	Response<String> registerEmployee(EmployeeDto employeeDto);

	Response<String> updateEmployee(EmployeeDto employeeDto);

	Response<String> departmentWiseCountOfEmployees(GetDepartmentWiseEmployeeCount getDepartmentWiseEmployeeCount);

	Response<String> employeesBelongsToParticularCity(GetCityWiseEmployeeCount getCityWiseEmployeeCount);

}
