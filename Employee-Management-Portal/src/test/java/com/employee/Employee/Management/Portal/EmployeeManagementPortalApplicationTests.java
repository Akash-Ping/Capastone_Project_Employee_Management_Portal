package com.employee.Employee.Management.Portal;

import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.ResourceRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.RequestResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeManagementPortalApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RequestResourceService requestResourceService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private ProjectRepository projectRepository;


	@Test
	void contextLoads() {
		// Ensure that the Spring application context loads successfully
	}
	@Test
	void testRequestResourceServiceNotNull() {
		assertNotNull(requestResourceService, "RequestResourceService should not be null");
	}

	@Test
	void testUserRepositoryNotNull() {
		assertNotNull(userRepository, "UserRepository should not be null");
	}

	@Test
	void testResourceRepositoryNotNull() {
		assertNotNull(resourceRepository, "ResourceRepository should not be null");
	}

	@Test
	void testProjectRepositoryNotNull() {
		assertNotNull(projectRepository, "ProjectRepository should not be null");
	}


}