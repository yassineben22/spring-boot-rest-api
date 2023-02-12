package com.example.demo.api.controller;

import com.example.demo.api.advice.validation.InsertValidation;
import com.example.demo.api.advice.validation.UpdateValidation;
import com.example.demo.api.dto.UserRequest;
import com.example.demo.api.exception.UserNotFound;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.UserRepository;
import com.example.demo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/api")
public class MainController {
    @Autowired
    private UserService service;

    @PostMapping(path="/users")
    public ResponseEntity<User> addNewUser(@Validated(InsertValidation.class) @RequestBody UserRequest request) {
        return new ResponseEntity<>(service.addUser(request), HttpStatus.CREATED);
    }

    @PutMapping (path="/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Validated(UpdateValidation.class) @RequestBody UserRequest request) throws UserNotFound {
        return new ResponseEntity<>(service.updateUser(id, request), HttpStatus.OK);
    }

    @GetMapping(path="/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws UserNotFound {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer id) throws UserNotFound {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }
}