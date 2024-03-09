package com.jencys.apicreateuser.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jencys.apicreateuser.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void given_a_invalid_parameter_will_return_400() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("   ", "juan@rodriguez.org", "Hunter2A2@2", Collections.emptyList());

        mockMvc.perform(post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRequestDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje").value("Error intentando persistir al usuario: [, name: must not be blank]"));
    }

    @Test
    void given_a_valid_parameter_will_return_200() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO("any-name", "juan@rodriguez.org", "Hunter2A2@2", Collections.emptyList());

        mockMvc.perform(post("/api/v1/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userRequestDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}