package management.mail.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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