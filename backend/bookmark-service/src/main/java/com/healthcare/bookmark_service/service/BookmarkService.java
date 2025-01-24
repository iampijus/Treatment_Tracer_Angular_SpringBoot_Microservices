package com.healthcare.bookmark_service.service;

import com.healthcare.bookmark_service.model.Bookmark;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookmarkService {
    public Bookmark addBookmark(Bookmark bookmark);
    public List<Bookmark> getAllBookmarks();
    public Optional<Bookmark> getBookmarkById(String id);
    public Map<String,String> deleteBookmark(String id);
    List<Bookmark> getBookmarksByUsername(String username);
}
