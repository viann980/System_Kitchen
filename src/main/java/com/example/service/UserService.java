package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.UserRepository;
import com.example.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String User) {
        return userRepository.findByUsername(User);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

	public User findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
