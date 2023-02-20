package com.example.demo.api.service;

import com.example.demo.api.dto.UserDto;
import com.example.demo.api.advice.exception.UserNotFound;
import com.example.demo.api.mappers.MapStruct;
import com.example.demo.api.model.Appartement;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.AppartementRepository;
import com.example.demo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private AppartementRepository appartementRepository;

    public Iterable<UserDto> getAllUsers(){
        return MapStruct.INSTANCE.ListUsersToListUserDtos((List<User>) repository.findAll());
    };

    public UserDto getUser(Long id) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");
        return MapStruct.INSTANCE.UserToUserDto(user);
    }

    public User addUser(UserDto request) throws ParseException {
        User user = MapStruct.INSTANCE.UserDtoToUser(request);
        user.getAppartements().forEach(app -> {
            app.setUser(user);
        });
        return repository.save(user);
    }


    public UserDto updateUser(Long id, UserDto request) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");

        if(request.getAppartements() != null){
            appartementRepository.deleteAll(appartementRepository.findByUserId(id));
        }

        user = MapStruct.INSTANCE.updateUser(user, request);
        for (Appartement appartement : user.getAppartements()) {
            appartement.setUser(user);
        }
        return MapStruct.INSTANCE.UserToUserDto(repository.save(user));
    }

    public Map<String, String> deleteUser(Long id) throws UserNotFound {
        if(!repository.existsById(id))
            throw new UserNotFound("Utilisateur introuvable!");

        repository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur supprimé!");
        return response;

    }
}
