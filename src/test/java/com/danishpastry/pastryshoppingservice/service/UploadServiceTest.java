package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import com.danishpastry.pastryshoppingservice.repository.ThumbnailRepository;
import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import com.danishpastry.pastryshoppingservice.service.impl.DefaultUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UploadServiceTest {

    @Mock
    private StorageService storageService;
    @Mock
    private ThumbnailRepository repository;

    @InjectMocks
    private UploadService service = new DefaultUploadService(storageService, repository);

    @Test
    public void shouldUploadProductImage() throws Exception {
        File file = new File("src/test/resources/image.jpg");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.jpg",
                new FileInputStream(file));
        when(storageService.upload(file)).thenReturn("url");
        when(repository.save(any(Thumbnail.class))).thenReturn(Thumbnail.builder().id("1234").build());
        UploadResult result = service.upload(mockMultipartFile);

        assertThat(result.getId()).isEqualTo("1234");

    }
}
