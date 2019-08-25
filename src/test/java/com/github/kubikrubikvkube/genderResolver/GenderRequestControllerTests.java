package com.github.kubikrubikvkube.genderResolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GenderRequestController.class)
class GenderRequestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenderRequestController service;

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    @BeforeEach
    void setUp() {
        var mockRequest = new NameRequest();
        mockRequest.setName("василиса");
        Mockito.when(service.resolve(mockRequest)).thenReturn(ResponseEntity.ok("{\"gender\":\"FEMALE\"}"));
    }

    @Test
    @Disabled
    void errorShouldBeThrownIfNameIsNull() throws Exception {
        this.mockMvc.perform(post("/name-resolver")
                .content("{\"name\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        )
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required NameRequest parameter 'name' is not present"))
                .andDo(print());

    }

    @Test
    void noErrorsShouldBeThrownIfNameIsValid() throws Exception {
        var content = "{\"name\": \"василиса\"}";
        this.mockMvc.perform(post("/name-resolver")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        )
                .andExpect(status().isOk())
                .andExpect(content().json("{\"gender\":\"FEMALE\"}"))
                .andDo(print());

    }
}
