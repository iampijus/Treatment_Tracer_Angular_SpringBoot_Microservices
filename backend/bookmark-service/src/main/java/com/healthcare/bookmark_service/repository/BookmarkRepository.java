package com.healthcare.bookmark_service.repository;

import com.healthcare.bookmark_service.model.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark,String> {
    List<Bookmark> findByUsername(String username);
}
