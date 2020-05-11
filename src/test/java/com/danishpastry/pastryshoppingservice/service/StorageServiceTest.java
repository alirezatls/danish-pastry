package com.danishpastry.pastryshoppingservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class StorageServiceTest {

    @Autowired
    private StorageService service;

    @Test
    public void shouldUploadToCloudStorage() {
        File file = new File("src/test/resources/image.jpg");
        String url = service.upload(file);

        assertThat(url).isNotNull();
    }
}
