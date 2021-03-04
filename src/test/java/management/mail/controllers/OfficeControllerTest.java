package management.mail.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import management.mail.dto.OfficeDto;
import management.mail.servicesinterface.OfficeServiceInterface;
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
 * Тесты для OfficeController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {OfficeController.class})
@ExtendWith(SpringExtension.class)
public class OfficeControllerTest {

  @Autowired
  private OfficeController officeController;

  @MockBean
  private OfficeServiceInterface officeServiceInterface;

  /**
   * Тест метода findAll
   *
   * @throws Exception
   */
  @Test
  public void testFindAll() throws Exception {
    when(this.officeServiceInterface.findAll()).thenReturn(new ArrayList<OfficeDto>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offices");
    MockMvcBuilders.standaloneSetup(this.officeController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
  }

  /**
   * Тест метода getOne
   *
   * @throws Exception
   */
  @Test
  public void testGetOne() throws Exception {
    when(this.officeServiceInterface.getOne((Long) any())).thenReturn(new OfficeDto());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/office/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.officeController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers
                .containsString("{\"id\":null,\"index\":null,\"name\":null,\"address\":null}")));
  }

  /**
   * Тест метода newOffice
   *
   * @throws Exception
   */
  @Test
  public void testNewOffice() throws Exception {
    when(this.officeServiceInterface.newOffice((OfficeDto) any())).thenReturn(new OfficeDto());
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/newoffice")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(new OfficeDto()));
    MockMvcBuilders.standaloneSetup(this.officeController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers
                .containsString("{\"id\":null,\"index\":null,\"name\":null,\"address\":null}")));
  }

  /**
   * Тест метода edit
   *
   * @throws Exception
   */
  @Test
  public void testEdit() throws Exception {
    when(this.officeServiceInterface.edit((Long) any(), (OfficeDto) any()))
        .thenReturn(new OfficeDto());
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
        .put("/editoffice/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(new OfficeDto()));
    MockMvcBuilders.standaloneSetup(this.officeController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers
                .containsString("{\"id\":null,\"index\":null,\"name\":null,\"address\":null}")));
  }

  /**
   * Тест метода delete
   *
   * @throws Exception
   */
  @Test
  public void testDelete() throws Exception {
    doNothing().when(this.officeServiceInterface).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/deleteoffice/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.officeController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

