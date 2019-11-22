@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerStatusController.class)
@AutoConfigureMockMvc(secure=false)
public class CustomerStatusControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerStatusService mockCustomerStatusService;
    
    @InjectMocks
    CustomerStatusController customerStatusController;
    
    @Test
    @WithMockUser
    public void getCustomerStatus() throws Exception {
//        CustomerStatus csStatus = new CustomerStatus("m", 0, 0, 1000);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonCustomer=null;
//        try {
//          jsonCustomer = mapper.writeValueAsString(csStatus);
//          System.out.println("ResultingJSONstring = " + jsonCustomer);
//        } catch (JsonProcessingException e) {
//           e.printStackTrace();
//        }
//        when(mockCustomerStatusService.getCustomerStatus("m")).thenReturn(csStatus);
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        CustomerStatus customer = customerStatusController.requestCustomerStatus(request, "m");
//        System.out.println(customer.getCustomerId());
//        this.mockMvc.perform(get("/customerstatus/{userid}", "m").param("userid","m").accept(MediaType.APPLICATION_JSON))
//            //.andExpect(status().isOk())
//        .andDo(MockMvcResultHandlers.print())
//            .andExpect(content().string(containsString("test")));
//            //.andExpect(jsonPath("$.customerId()", is("m")));
    }
    
//    @Test
//    public void getTestCustomer() throws Exception {
//        String test = "test";
//        when(mockCustomerStatusService.getTestCustomer()).thenReturn(test);
//        this.mockMvc.perform(get("/testCustomer").contentType(APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().string(containsString("test")));
//    }
}