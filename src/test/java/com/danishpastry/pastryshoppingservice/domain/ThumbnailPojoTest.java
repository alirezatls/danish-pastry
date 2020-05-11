package com.danishpastry.pastryshoppingservice.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThumbnailPojoTest {

    @Test
    public void testPojo() {
        Thumbnail thumbnail = Thumbnail.builder()
                .name("image.jpg")
                .build();
        assertThat(thumbnail.getName()).isEqualTo("image.jpg");
    }
}
