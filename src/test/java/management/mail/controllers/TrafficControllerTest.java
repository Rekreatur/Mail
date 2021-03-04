package management.mail.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import management.mail.constants.TrafficOfficeStatusEnum;
import management.mail.dto.TrafficDto;
import management.mail.servicesinterface.TrafficServiceInterface;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
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
  private TrafficServiceInterface trafficServiceInterface;

  @Test
  public void testFindAll() throws Exception {
    when(this.trafficServiceInterface.findAll()).thenReturn(new ArrayList<TrafficDto>());
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
    when(this.trafficServiceInterface.getOne((Long) any()))
        .thenReturn(new TrafficDto(123L, 1L, 1L, TrafficOfficeStatusEnum.ARRIVED,
            LocalDateTime.of(1, 1, 1, 1, 1)));
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/traffic/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers.containsString(
                "{\"id\":123,\"mail_id\":1,\"post_office_id\":1,\"status\":\"ARRIVED\",\"date\":\"0001-01-01 01:01:00\"}")));
  }

  @Test
  public void testGetPath() throws Exception {
    when(this.trafficServiceInterface.getPath((Long) any()))
        .thenReturn(new ArrayList<TrafficDto>());
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
    when(this.trafficServiceInterface.getStatus((Long) any())).thenReturn("foo");
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
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
        .delete("https://example.org/example", "foo", "foo", "foo")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(
            new TrafficDto(123L, 1L, 1L, TrafficOfficeStatusEnum.ARRIVED,
                LocalDateTime.of(1, 1, 1, 1, 1))));
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.trafficController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}

