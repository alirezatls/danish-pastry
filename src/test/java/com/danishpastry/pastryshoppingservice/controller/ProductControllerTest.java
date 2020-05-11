package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.service.ProductService;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenCreateProduct_thenReturn200() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("iphone");
        ProductResponse response = new ProductResponse();
        response.setName("iphone");
        String json = new ObjectMapper().writeValueAsString(request);
        when(service.create(request)).thenReturn(response);
        mockMvc.perform(post("/api/user/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
