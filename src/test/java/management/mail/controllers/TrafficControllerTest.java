package management.mail.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import management.mail.dto.TrafficDto;
import management.mail.interservices.TrafficServiceInter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Тесты для TrafficController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {TrafficController.class})
@ExtendWith(SpringExtension.class)
public class TrafficControllerTest {

  @Autowired
  private TrafficController trafficController;

  @MockBean
  private TrafficServiceInter trafficServiceInter;

  @Test
  public void testFindAll() throws Exception {
    when(this.trafficServiceInter.findAll()).thenReturn(new ArrayList<TrafficDto>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/traffic");
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
  }

  @Test
  public void testGetOne() throws Exception {
    when(this.trafficServiceInter.getOne((Long) any())).thenReturn(new TrafficDto());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/traffic/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers.containsString(
                "{\"id\":null,\"mail_id\":null,\"post_office_id\":null,\"status\":null,\"date\":null}")));
  }


  @Test
  public void testGetPath() throws Exception {
    when(this.trafficServiceInter.getPath((Long) any())).thenReturn(new ArrayList<TrafficDto>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fullpath/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
  }


  @Test
  public void testGetStatus() throws Exception {
    when(this.trafficServiceInter.getStatus((Long) any())).thenReturn("foo");
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("foo")));
  }


  @Test
  public void testNewTraffic() throws Exception {
    when(this.trafficServiceInter.newTraffic((TrafficDto) any())).thenReturn("foo");
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/newtraffic")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(new TrafficDto()));
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("foo")));
  }
}

