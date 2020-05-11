package com.danishpastry.pastryshoppingservice.util;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPartFileUtilTest {

    @Test
    public void shouldConvertMultipartFileToFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.jpg",
                new FileInputStream(new File("src/test/resources/image.jpg")));
        MultipartFileUtil util = new MultipartFileUtil(mockMultipartFile);
        File file = util.toFile();

        assertThat(file.exists()).isTrue();
        assertThat(file.getName()).startsWith("temp_");
        assertThat(file.delete()).isTrue();
    }
}
