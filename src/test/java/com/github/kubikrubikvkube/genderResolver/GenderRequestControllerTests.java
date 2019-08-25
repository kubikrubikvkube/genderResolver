package com.github.kubikrubikvkube.genderResolver;

import org.junit.jupiter.api.BeforeEach;
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
public class GenderRequestControllerTests {
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
        Mockito.when(service.resolve(new NameRequest("василиса"))).thenReturn(ResponseEntity.ok("{\"gender\":\"FEMALE\"}"));
    }

    @Test
    public void errorShouldBeThrownIfNameIsNull() throws Exception {
        this.mockMvc.perform(post("/name-resolver"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required NameRequest parameter 'name' is not present"))
                .andDo(print());

    }

    @Test
    public void noErrorsShouldBeThrownIfNameIsValid() throws Exception {
        var name = encodeValue("василиса");
        var content = String.format("name=%s", name);
        this.mockMvc.perform(post("/name-resolver")
                .content(content)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("UTF-8")
        )
                .andExpect(status().isOk())
                .andExpect(content().json("{\"gender\":\"FEMALE\"}"))
                .andDo(print());

    }
}
