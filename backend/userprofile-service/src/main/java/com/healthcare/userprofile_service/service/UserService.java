package com.healthcare.userprofile_service.service;

import com.healthcare.userprofile_service.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public List<User> getAllUsers();
    public Optional<User> getUserByUsername(String username);
    public Optional<User> getUserById(Long id);
    public User updateUser(Long id,User user);
    public Map<String,String> deleteUser(Long id);
}
