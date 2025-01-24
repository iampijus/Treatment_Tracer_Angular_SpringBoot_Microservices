package com.healthcare.bookmark_service.service;

import com.healthcare.bookmark_service.exception.BookmarkAlreadyExistsException;
import com.healthcare.bookmark_service.model.Bookmark;
import com.healthcare.bookmark_service.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;


    @Override
    public Bookmark addBookmark(Bookmark bookmark) {

        List<Bookmark> existingBookmarks = bookmarkRepository.findByUsername(bookmark.getUsername());
        for (Bookmark existingBookmark : existingBookmarks) {
            if (existingBookmark.getId().equals(bookmark.getId())) {
                throw new BookmarkAlreadyExistsException("Bookmark with the given ID already exists for this user");
            }
        }
        return bookmarkRepository.save(bookmark);
    }

    @Override
    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    @Override
    public Optional<Bookmark> getBookmarkById(String id) {
        return bookmarkRepository.findById(id);
    }

    @Override
    public Map<String, String> deleteBookmark(String id) {
        Optional<Bookmark> bookmarkOptional = bookmarkRepository.findById(id);

        Map<String, String> res = new HashMap<>();

        if (bookmarkOptional.isPresent()) {
            Bookmark bookmark = bookmarkOptional.get();
            bookmarkRepository.delete(bookmark);
            res.put("deleted", "success");
        } else {
            res.put("deleted", "failed");
            res.put("message", "Bookmark not found with id: " + id);

        }
        return res;

    }

    @Override
    public List<Bookmark> getBookmarksByUsername(String username) {
        return bookmarkRepository.findByUsername(username);
    }
}
