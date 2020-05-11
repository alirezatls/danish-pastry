package com.danishpastry.pastryshoppingservice.repository;

import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThumbnailRepository extends MongoRepository<Thumbnail,String> {
}
