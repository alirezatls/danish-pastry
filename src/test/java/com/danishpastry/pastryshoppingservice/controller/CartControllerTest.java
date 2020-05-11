package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.domain.CartItem;
import com.danishpastry.pastryshoppingservice.service.CartItemService;
import com.danishpastry.pastryshoppingservice.service.dto.CartRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @MockBean
    private CartItemService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenAddToCart_thenReturn200() throws Exception {
        CartRequest request = new CartRequest();
        request.setName("iphone");
        String json = new ObjectMapper().writeValueAsString(request);
        doNothing().when(service).addToCart(request);
        mockMvc.perform(post("/api/common/cart")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenDeleteItem_thenReturn200() throws Exception {
        doNothing().when(service).deleteItem("1234");
        mockMvc.perform(delete("/api/common/cart/{id}", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenGetAllItem_thenReturn200() throws Exception {
        List<CartItem> cartItems = Arrays.asList(new CartItem("1", "iphone", 1200d), new CartItem("2", "s8", 900d));
        when(service.getAllItem()).thenReturn(cartItems);
        mockMvc.perform(get("/api/common/cart"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
