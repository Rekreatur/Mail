package management.mail.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import management.mail.domain.Traffic;
import management.mail.misc.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrafficControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void list() throws Exception{
        this.mockMvc.perform(get("/traffic")).andExpect(status().isOk());
    }

    @Test
    void getOne() throws Exception {
        this.mockMvc.perform(get("/traffic/5")).andExpect(status().isOk());
    }

    @Test
    void get_path() throws Exception {
        this.mockMvc.perform(get("/fullpath/1")).andExpect(status().isOk());
    }

    @Test
    void get_status() throws Exception {
        this.mockMvc.perform(get("/status/15")).andExpect(status().isOk());
    }

    @Test
    void new_traffic() throws Exception{
        String s = "{\n" +
                "    \"mail_id\": 1,\n" +
                "    \"post_office_id\": 9,\n" +
                "    \"status\": \"0\"" +
                "}";

        this.mockMvc.perform(post("/newtraff")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }
}