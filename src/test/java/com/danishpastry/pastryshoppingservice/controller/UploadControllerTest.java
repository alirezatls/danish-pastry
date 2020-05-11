package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.service.UploadService;
import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @MockBean
    private UploadService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUploadMultipartFile_thenReturn200() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.jpg",
                new FileInputStream(new File("src/test/resources/image.jpg")));
        when(service.upload(any(MultipartFile.class))).thenReturn(UploadResult.of("1234"));
        MvcResult result = mockMvc.perform(multipart("/api/user/upload")
                .file("file", mockMultipartFile.getBytes()))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk());
    }
}
