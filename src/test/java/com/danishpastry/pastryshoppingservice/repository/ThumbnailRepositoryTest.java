package com.danishpastry.pastryshoppingservice.repository;

import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ThumbnailRepositoryTest {

    @Autowired
    private ThumbnailRepository repository;

    @Before
    public void before() {
        this.repository.deleteAll();
    }

    @After
    public void after() {
        this.repository.deleteAll();
    }

    @Test
    public void shouldPersistThumbnail() {
        Thumbnail build = Thumbnail.builder().name("image.jpg").build();
        Thumbnail save = repository.save(build);

        assertThat(save.getName()).isEqualTo("image.jpg");
        assertThat(save.getId()).isNotNull();
    }
}
