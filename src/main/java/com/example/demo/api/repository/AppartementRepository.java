package com.example.demo.api.repository;

import com.example.demo.api.model.Appartement;
import com.example.demo.api.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppartementRepository extends CrudRepository<Appartement, Long> {
    public List<Appartement> findByUserId(Long id);
}
