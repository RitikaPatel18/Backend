package backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.backend.BackendApplication;
import com.project.backend.Response;
import com.project.backend.User;
import com.project.backend.UserController;
import com.project.backend.UserRepository;
import com.project.backend.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class ServiceRepositoryTest {
	
	@InjectMocks
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserRepository repository;

	@Test
	public void getAllUsersTest() {
		when(repository.findAll()).thenReturn(Stream.of
				(new User(1, "Sakshi", "Gupta","sakshi1", "sakshi@gmail.com", "sakshi123"),
				new User(2, "Ritika", "Patel","ritika1", "ritika@gmail.com", "12345")).collect(Collectors.toList()));
		assertEquals(2, service.getAllUsers().size());
	}
	
	@Test
	public void registerTest() {
		User user=new User();
		user.setEmail("rohit@gmail.com");
		user.setFirstName("Rohit");
		user.setLastName("Mangwani");
		user.setUsername("rohit1");
		user.setPassword("rohit123");
//		service.register(user);
		
		when(repository.save(user)).thenReturn(
		(new User(3, "Rohit", "Mangwani","rohit1", "rohit@gmail.com", "rohit123")));
		
		assertEquals("rohit@gmail.com",user.getEmail());
	}
	
	
	@Test
	public void loginTest() {
		User user=new User();
		user.setUsername("sakshi1");
		user.setPassword("sakshi123");
//		repository.save(user);
	    when(repository.findByEmailAndPassword("sakshi@gmail.com", "sakshi123")).thenReturn(new User("sakshi1","sakshi123"));
	    assertEquals(HttpStatus.ACCEPTED,service.login(user).getStatusCode());
				
	}
}
