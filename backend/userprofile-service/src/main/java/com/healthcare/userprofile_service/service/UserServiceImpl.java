package com.healthcare.userprofile_service.service;

import com.healthcare.userprofile_service.event.UserCredentialEvent;
import com.healthcare.userprofile_service.exception.UserAlreadyExistsException;
import com.healthcare.userprofile_service.model.User;
import com.healthcare.userprofile_service.model.UserCredentialDto;
import com.healthcare.userprofile_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, UserCredentialEvent> kafkaTemplate;


    @Override
    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with the given email already exists");
        }

        User savedUser=userRepository.save(user);

        UserCredentialDto userCredentialDto=new UserCredentialDto(savedUser.getEmail(),savedUser.getPassword());
        UserCredentialEvent event=new UserCredentialEvent("user-registration",userCredentialDto);

        kafkaTemplate.send("auth_credentials",event);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setMobile(user.getMobile());
            userToUpdate.setAge(user.getAge());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setPassword(user.getPassword());

            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public Map<String, String> deleteUser(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        Map<String, String> res = new HashMap<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            res.put("deleted", "success");
        } else {
            res.put("deleted", "failed");
            res.put("message", "User not found with id: " + id);

        }
        return res;
    }
}
