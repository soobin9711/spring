package com.study.mint.mintweb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.study.mint.mintweb.User;
import com.study.mint.mintweb.UserRepository;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        //this.userRepository.save(user);


        try{
            this.userRepository.save(user);
        }
        catch(DataIntegrityViolationException e){
            System.out.println("이미 존재하는 email 입니다");
        }

    }

    @Override
    public User getUserById(long id) {
        Optional < User > optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }


    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);

    }

   // @Override
    //public int duplicateCheck(User user) throws Exception{
     //   int result = User.duplicateCheck(user);
   // }
}
