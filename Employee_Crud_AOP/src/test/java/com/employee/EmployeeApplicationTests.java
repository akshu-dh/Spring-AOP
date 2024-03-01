package com.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.controller.EmployeeController;
import com.employee.exception.AuthorizationException;
import com.employee.model.Employee;
import com.employee.repo.EmployeeRepo;
import com.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {
	
	@Autowired
	private EmployeeController employeeController;
	
	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepo employeeRepo;

	@Test
	public void contextLoads() {
		assertEquals(1,1);
	}
	
	
	@Test
    public void testaddEmployee() throws AuthorizationException {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Akshu");
		employee.setDept("CS");
		employee.setAddress("Pune");
		employee.setSalary(5000000);
        Mockito.when(employeeService.addEmployee(employee)).thenReturn(employee);
        assertEquals(employee, employeeController.addEmployee(employee));
    }
		
	@Test
    public void testgetAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employees);
        assertEquals(employees, employeeController.getAllEmployees());
    }
	
	
	@Test
    public void testgetEmployeeById() {
        Mockito.when(employeeService.getEmployeeById(102)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        assertEquals(new ResponseEntity<>(HttpStatus.OK), employeeController.getEmployeeById(102));
    }
	
	@Test
    public void testdeleteEmployee() {
        Mockito.when(employeeService.deleteEmployee(405))
                .thenReturn(new ResponseEntity<String>("student deleted successfully!", HttpStatus.OK));
        assertEquals(new ResponseEntity<String>("student deleted successfully!", HttpStatus.OK),
        		employeeController.deleteEmployee(405));
    }
}
