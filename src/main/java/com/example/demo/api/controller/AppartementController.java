package com.example.demo.api.controller;

import com.example.demo.api.dto.AppartementDto;
import com.example.demo.api.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/appartements")
public class AppartementController {
    @Autowired
    private AppartementService service;

    @GetMapping
    public ResponseEntity<List<AppartementDto>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
