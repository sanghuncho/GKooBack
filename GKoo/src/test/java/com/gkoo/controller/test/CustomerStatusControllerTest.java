package com.gkoo.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.gkoo.app.Application;
import com.gkoo.controller.CustomerStatusController;
import com.gkoo.data.CustomerStatus;
import com.gkoo.service.CustomerStatusService;
import com.gkoo.service.impl.CustomerStatusServiceImpl;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CustomerStatusServiceImpl.class)
//@AutoConfigureMockMvc(secure=false)
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerStatusController.class)
@ContextConfiguration(classes = Application.class)
public class CustomerStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerStatusService mockCustomerStatusService;
    
    @InjectMocks
    CustomerStatusController customerStatusController;
    
    @Autowired
    WebApplicationContext webApplicationContext;
    
//    @Before
//    public void addData() {
//        mockMvc = MockMvcBuilders.standaloneSetup(customerStatusController)
//                .build();
//    }
    
    @Test
    public void getCustomerStatus() throws Exception {
       
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String uri = "/customerstatus/m";
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//           .accept(APPLICATION_JSON)).andReturn();        
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(202, status);

        
//        CustomerStatus csStatus = new CustomerStatus("m", 0, 0, 1000);
//        when(mockCustomerStatusService.getCustomerStatus("m")).thenReturn(csStatus);
//        this.mockMvc.perform(get("/customerstatus/{userid}", "m").param("userid","m").accept(APPLICATION_JSON))
//        .andExpect(status().isOk());
        
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonCustomer=null;
//        try {
//          jsonCustomer = mapper.writeValueAsString(csStatus);
//          System.out.println("ResultingJSONstring = " + jsonCustomer);
//        } catch (JsonProcessingException e) {
//           e.printStackTrace();
//        }
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        CustomerStatus customer = customerStatusController.requestCustomerStatus(request, "m");
//        System.out.println(customer.getCustomerId());
        //.andDo(MockMvcResultHandlers.print())
            //.andExpect(content().string(containsString("test")));
//        .andExpect(status().isOk());
            //.andExpect(jsonPath("$.customerId()", is("m")));
    }
}
