package com.emp.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.emp.management.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> 
{
	@Query(value = "select department, COUNT(*) from employee GROUP BY department", nativeQuery = true)
	List<String> getDepartmentWiseCount();
	
//	@Query(value = "select COUNT(*) from employee WHERE department = ?1;", nativeQuery = true)
//	List<String> getDepartmentWiseCount(String department);
	
	
// EMPLOYEES FROM PARTICULAR CITY	
	//1
	@Query(value = "select MAX(city) as city, COUNT(*) as count from employee GROUP BY city", nativeQuery = true)
	List<String> allEmployeesBelongsToParticularCity();
	
	//2
	@Query(value = "select TOP 1 MAX(city) as city, COUNT(*) as count from employee GROUP BY city", nativeQuery = true)
	String topEmployeesBelongsToParticularCity();
	
	//3
	@Query(value = "select MAX(city) as city, COUNT(*) as count from employee WHERE city = ?1", nativeQuery = true)
	String employeesBelongsToParticularCity(String city);

}
