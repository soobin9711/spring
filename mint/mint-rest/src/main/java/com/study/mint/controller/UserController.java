package com.study.mint.controller;

import com.study.mint.User;
import com.study.mint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping(value="/api/v1")
    public class UserController {
        @Autowired
        private UserRepository userRepository;

        @GetMapping("/users")
        public List <User> getAllUsers() {
            return userRepository.findAll();
        }

        @GetMapping("/users/{id}")
        public ResponseEntity <User> getUserById(@PathVariable(value = "id") Long userId) {
            User user = userRepository.findById(userId).orElseThrow();
            return ResponseEntity.ok().body(user);
        }

        @PostMapping("/users")
        public User createUser( @RequestBody User user) {

            return userRepository.save(user);
        }

        @PutMapping("/users/{id}")
        public ResponseEntity < User > updateUser(@PathVariable(value = "id") Long userId,
                                                  @RequestBody User userDetails) {
            User user = userRepository.findById(userId).orElseThrow();

            user.setEmailId(userDetails.getEmailId());
            user.setLastName(userDetails.getLastName());
            user.setFirstName(userDetails.getFirstName());
            final User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }

        @DeleteMapping("/users/{id}")
        public Map < String, Boolean > deleteUser(@PathVariable(value = "id") Long userId) {
            User user = userRepository.findById(userId).orElseThrow();

            userRepository.delete(user);
            Map < String, Boolean > response = new HashMap < > ();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
}
