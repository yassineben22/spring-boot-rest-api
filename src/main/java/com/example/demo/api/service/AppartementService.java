package com.example.demo.api.service;

import com.example.demo.api.advice.exception.AppartementNotFound;
import com.example.demo.api.advice.exception.UserNotFound;
import com.example.demo.api.dto.AppartementDto;
import com.example.demo.api.mappers.MapStruct;
import com.example.demo.api.model.Appartement;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.AppartementRepository;
import com.example.demo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppartementService {
    private MapStruct map = MapStruct.INSTANCE;
    @Autowired
    private AppartementRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<AppartementDto> getAll(){
        return map.ListAppartementsToListAppartementDtos((List<Appartement>) repository.findAll());
    }

    public AppartementDto getAppartement(Long id) throws AppartementNotFound {
        Appartement appartement = repository.findById(id).orElse(null);
        if(appartement == null)
            throw new AppartementNotFound("Appartement introuvable!");
        return map.AppartementToAppartementDto(appartement);
    }

    public AppartementDto addAppartement(AppartementDto request, Long userId) throws UserNotFound {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");
        Appartement appartement = map.AppartementDtoToAppartement(request);
        appartement.setUser(user);
        return map.AppartementToAppartementDto(repository.save(appartement));
    }

    public AppartementDto updateAppartement(AppartementDto request, Long id) throws AppartementNotFound {
        Appartement appartement = repository.findById(id).orElse(null);
        if(appartement == null)
            throw new AppartementNotFound("Appartement introuvable!");
        return map.AppartementToAppartementDto(
                repository.save(
                        map.updateAppartement(appartement, request)));
    }

    public AppartementDto updateAppartementUser(Long app_id, Long user_id) throws UserNotFound, AppartementNotFound {
        User user = userRepository.findById(user_id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur Introuvable!");

        Appartement appartement = repository.findById(app_id).orElse(null);
        if(appartement == null)
            throw new AppartementNotFound("Appartement introuvable!");
        appartement.setUser(user);
        return map.AppartementToAppartementDto(
                repository.save(appartement)
        );
    }

    public Map<String, String> deleteAppartement(Long id) throws AppartementNotFound {
        if(!repository.existsById(id))
            throw new AppartementNotFound("Appartement introuvable!");
        repository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur supprimé!");
        return response;
    }
}
