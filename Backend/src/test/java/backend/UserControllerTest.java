package backend;



import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.backend.BackendApplication;
import com.project.backend.User;
import com.project.backend.UserController;
import com.project.backend.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class UserControllerTest {
   @Mock
   private UserService userService;
   @InjectMocks
   private UserController userController;
   @Autowired
   private MockMvc mockMvc;
   @BeforeEach
   public void setup(){
   new User(1,"Ritika","Patel","ritika1","ritika@gmail.com","12345");
   mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
   }
   @AfterEach
   void tearDown() {
   }
   
   @Test
   public void testregister () throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/register").contentType(MediaType.APPLICATION_JSON)
                                                 .content(createUserInJson("Ritika",
                                                                           "Patel",
                                                                           "ritika1",
                                                                           "ritika_gmail.com",
                                                                           "12345", null));
      this.mockMvc.perform(builder) .andExpect(MockMvcResultMatchers.status().isOk());
      builder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);
       this.mockMvc.perform(builder) .andExpect(MockMvcResultMatchers.status().isOk());

  }

  private static String createUserInJson (String firstname, String lastname, String username, String email, String password, String number) {
       return "{ \"firstname\": \"" + firstname + "\", " +
                           "\"lastname\":\"" + lastname + "\"," +
                           "\"username\":\"" + username + "\"," +
                           "\"emailAddress\":\"" + email + "\"," +
                           "\"password\":\"" + password + "\"}";
   }
   
   @Test
   public void testlogin() throws Exception{
       MockHttpSession session = new MockHttpSession();

      User user = new User();
       user.setUsername("ritika1");
       user.setPassword("12345");
      session.setAttribute("user", user);
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user") .session(session);
       this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
   }
   
   @Test
   public void testGetAllUsers () throws Exception {
	   
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user");
       this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
   }
   }


























//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Map;
//
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.backend.BackendApplication;
//import com.project.backend.Response;
//import com.project.backend.User;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BackendApplication.class)
//public class SpringApplicationMokitoTest {
//
//	private MockMvc mockMvc;
//	
//	@Autowired
//	private WebApplicationContext context; //interact to controller
//	
//	//to convert the raw object into json or string json
//	ObjectMapper om=new ObjectMapper();
//	
//	@Before //initilize mock before loading class
//	public void setUp() {
//		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
//		
//	}
//	
//	@Test
//	public void loginTest() throws Exception {
//		User user=new User();
//		user.setUsername("ritika1");
//		user.setPassword("12345");
//		String jsonRequest=om.writeValueAsString(user);
//		MvcResult result=mockMvc.perform(
//				post("/user/login").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		//result (data) in the format of raw data
//		String resultContent=result.getResponse().getContentAsString();
//		Response res=om.readValue(resultContent, Response.class);
//		Assert.assertTrue(res.getStatus()==Boolean.TRUE);
//		
//	}
//	
//	@Test
//	public void registerTest() throws Exception {
//		User user=new User();
//		user.setFirstName("Sumit");
//		user.setLastName("Kumar");
//		user.setEmail("sumit@gmail.com");
//		user.setUsername("sumit1");
//		user.setPassword("sumit123");
//		String jsonRequest=om.writeValueAsString(user);
//		MvcResult result=mockMvc.perform(
//				post("/user/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		//result (data) in the format of raw data
//		String resultContent=result.getResponse().getContentAsString();
//		Response res=om.readValue(resultContent, Response.class);
//		Assert.assertTrue(res.getStatus()==Boolean.TRUE);
//	}
//	
//	@Test
//	public void getAllUsersTest() throws Exception {
//		
//		MvcResult result=mockMvc.perform(
//				post("/user").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn();
//		//result (data) in the format of raw data
//		String resultContent=result.getResponse().getContentAsString();
//		Response res=om.readValue(resultContent, Response.class);
//		Assert.assertTrue(res.getStatus()==Boolean.TRUE);
//	
//		}
//}


















