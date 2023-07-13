package com.transaction.devsu.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getClientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                .content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
        .content("{\n" +
                "    \"cedula\":\"1400919406\",\n" +
                "    \"nombre\":\"DEV SU TEST TRES\",\n" +
                "    \"genero\":\"FEMALE\",\n" +
                "    \"edad\":25,\n" +
                "    \"direccion\":\"Ecuador\",\n" +
                "    \"numeroTelefono\":\"0998952718\",\n" +
                "    \"contrasena\":\"Abc123!df\",\n" +
                "    \"estado\":true\n" +
                "}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
}