package management.mail.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import management.mail.constants.MailTypeEnum;
import management.mail.dto.MailDto;
import management.mail.servicesinterface.MailServiceInterface;
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
 * Тесты для MailController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {MailController.class})
@ExtendWith(SpringExtension.class)
public class MailControllerTest {

  @Autowired
  private MailController mailController;

  @MockBean
  private MailServiceInterface mailServiceInterface;

  @Test
  public void testFindAll() throws Exception {
    when(this.mailServiceInterface.findAll()).thenReturn(new ArrayList<MailDto>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mails");
    MockMvcBuilders.standaloneSetup(this.mailController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
  }

  @Test
  public void testGetOne() throws Exception {
    when(this.mailServiceInterface.getOne((Long) any()))
        .thenReturn(new MailDto(123L, MailTypeEnum.LETTER, "Index", "42 Main St", "Name"));
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mail/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.mailController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(Matchers.containsString(
                "{\"id\":123,\"type\":\"LETTER\",\"index\":\"Index\",\"address\":\"42 Main St\",\"name\":\"Name\"}")));
  }

  @Test
  public void testDelete() throws Exception {
    doNothing().when(this.mailServiceInterface).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/delete/{id}", 1L);
    MockMvcBuilders.standaloneSetup(this.mailController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testRegistration() throws Exception {
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
        .delete("https://example.org/example", "foo", "foo", "foo")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
        objectMapper.writeValueAsString(
            new MailDto(123L, MailTypeEnum.LETTER, "Index", "42 Main St", "Name")));
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.mailController)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}

