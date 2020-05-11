package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
public class UploadControllerIntegrationTest {

    @Autowired
    private UploadController controller;

    @Test
    public void whenUpload_thenReturn200() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.jpg",
                new FileInputStream(new File("src/test/resources/image.jpg")));
        CompletableFuture<UploadResult> result = controller.upload(mockMultipartFile);

        assertThat(result.get()).isNotNull();
        System.out.println(result.get());
    }
}
