package com.example.demo.api.service;

import com.example.demo.api.dto.UserRequest;
import com.example.demo.api.exception.UserNotFound;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Iterable<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUser(int id) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");
        return user;
    }

    public User addUser(UserRequest request){
        User user = User.create(0, request.getName(), request.getEmail());
        return repository.save(user);
    }

    public User updateUser(int id, UserRequest request) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");

        if(request.getName() != null)
            user.setName(request.getName());
        if(request.getEmail() != null)
            user.setEmail(request.getEmail());
        return repository.save(user);
    }

    public Map<String, String> deleteUser(int id) throws UserNotFound {
        if(!repository.existsById(id))
            throw new UserNotFound("Utilisateur introuvable!");

        repository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur supprimé!");
        return response;
    }
}
