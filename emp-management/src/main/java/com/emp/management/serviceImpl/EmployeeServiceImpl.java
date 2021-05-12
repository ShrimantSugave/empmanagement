package com.emp.management.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.emp.management.dao.EmployeeDao;
import com.emp.management.dto.EmployeeDto;
import com.emp.management.dto.GetCityWiseEmployeeCount;
import com.emp.management.dto.GetDepartmentWiseEmployeeCount;
import com.emp.management.entity.Employee;
import com.emp.management.model.Response;
import com.emp.management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeDao employeeDao;

	Logger log = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public Response<String> registerEmployee(EmployeeDto employeeDto)
	{		
		try
		{
			Employee employee = new Employee();
			
			employee.setFirst_name(employeeDto.getFirst_name());
			employee.setLast_name(employeeDto.getLast_name());
			employee.setCity(employeeDto.getCity());
			employee.setAge(employeeDto.getAge());
			employee.setSalary(employeeDto.getSalary());
			employee.setDepartment(employeeDto.getDepartment());
			
			employeeDao.save(employee);
			
			return new Response<>(HttpStatus.OK.value(), "Employee Added Successfully");			
		}
		catch(Exception e)
		{
			log.info(e);
			return new Response<>(201, "Something went Wrong....Try Later");			
		}
	}
	
	@Override
	public Response<String> updateEmployee(EmployeeDto employeeDto)
	{		
		try
		{
			Optional<Employee> employee = employeeDao.findById(employeeDto.getEmployee_id());
			
			if(employee.isPresent())
			{				
				employee.get().setFirst_name(employeeDto.getFirst_name());
				employee.get().setLast_name(employeeDto.getLast_name());
				employee.get().setCity(employeeDto.getCity());
				employee.get().setAge(employeeDto.getAge());
				employee.get().setSalary(employeeDto.getSalary());
				employee.get().setDepartment(employeeDto.getDepartment());
				
				employeeDao.save(employee.get());
				
				return new Response<>(200, "Employee "+employeeDto.getEmployee_id()+" Updated Successfully");
			}
			else
			{
				return new Response<>(200, "Employee Not Found");
			}						
		}
		catch(Exception e)
		{
			log.info(e);
			return new Response<>(201, "Something went Wrong....Try Later");			
		}
	}
	
	@Override
	public Response<String> departmentWiseCountOfEmployees(GetDepartmentWiseEmployeeCount getDepartmentWiseEmployeeCount)
	{		
		try
		{
			List<String> departmentCount = employeeDao.getDepartmentWiseCount();
			
			//Long departmentCount = employeeDao.getDepartmentWiseCount(getDepartmentWiseEmployeeCount.getDepartment());
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			for(int i = 0; i<departmentCount.size(); i++)
			{
				String[] d = departmentCount.get(i).split(",");
				
				json1.put(d[0], d[1]);
			}
			
			json2.put("departmentCount", json1);
			List abc = new ArrayList<>();
			abc.add(json1);
			
			return new Response<>(200, abc.toString());
		}
		catch(Exception e)
		{
			log.error(e);
			return new Response<>(201, "Something went Wrong....Try Later");			
		}
	}
	
	@Override
	public Response<String> employeesBelongsToParticularCity(GetCityWiseEmployeeCount getCityWiseEmployeeCount)
	{		
		try
		{
			//1
/*			
			List<String> employeeCount = employeeDao.allEmployeesBelongsToParticularCity();
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			
			for(int i = 0; i<employeeCount.size(); i++)
			{
				String[] d = employeeCount.get(i).split(",");
				
				json1.put(d[0], d[1]);
			}
			
			json2.put("EmployeeCount", json1);
			
			return new Response<>(200, json2.toString());
*/			
			//2		
			//String employeeCount = employeeDao.topEmployeesBelongsToParticularCity();
			
			//3
			String employeeCount = employeeDao.employeesBelongsToParticularCity(getCityWiseEmployeeCount.getCity());
			
			System.out.println(employeeCount);
			
			if(employeeCount.contains("null"))
			{			
				return new Response<>(200, "City Not Present");
			}
			else
			{
				String[] d = employeeCount.split(",");
				
				JSONObject json1 = new JSONObject();
				
				json1.put(d[0], d[1]);
				
				return new Response<>(200, json1.toString());
			}		
		}
		catch(Exception e)
		{
			log.info(e);
			return new Response<>(201, "Something went Wrong....Try Later");			
		}
	}
}
