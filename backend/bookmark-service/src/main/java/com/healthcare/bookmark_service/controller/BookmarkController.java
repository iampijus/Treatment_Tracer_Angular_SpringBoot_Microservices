package com.healthcare.bookmark_service.controller;

import com.healthcare.bookmark_service.model.Bookmark;
import com.healthcare.bookmark_service.service.BookmarkService;
import com.healthcare.bookmark_service.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/healthcare/v3/")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/bookmarks")
    public ResponseEntity<Bookmark> addBookmark(@RequestBody Bookmark bookmark, HttpServletRequest request) {
        try {
            // getting the username or email from the jwt token
            String token = request.getHeader("Authorization").substring(7);
            String username = jwtUtil.extractUsername(token);
            bookmark.setUsername(username);
            // sending to service layer after setting username
            Bookmark newBookmark = bookmarkService.addBookmark(bookmark);
            return ResponseEntity.ok(newBookmark);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/bookmarks/all")
    public ResponseEntity<List<Bookmark>> getAllBookmarks() {
        List<Bookmark> list = bookmarkService.getAllBookmarks();
        if (list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/bookmarks/{id}")
    public ResponseEntity<Optional<Bookmark>> getBookmarkById(@PathVariable("id") String id) {
        try {
            Optional<Bookmark> bookmark = bookmarkService.getBookmarkById(id);
            if (bookmark.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(bookmark);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/bookmarks/{id}")
    public ResponseEntity<Map<String, String>> deleteBookmark(@PathVariable String id) {
        try {
            Map<String, String> res = bookmarkService.deleteBookmark(id);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // To get bookmarks by username from JWT token
    @GetMapping("/bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarksByUsername(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String username = jwtUtil.extractUsername(token);
            List<Bookmark> bookmarks = bookmarkService.getBookmarksByUsername(username);
            if (bookmarks.isEmpty()) {
                return ResponseEntity.ok(bookmarks);
            }
            return ResponseEntity.ok(bookmarks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
