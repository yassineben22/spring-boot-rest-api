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

    private MapStruct map = MapStruct.INSTANCE;

    public List<UserDto> getAllUsers(){
        return map.ListUsersToListUserDtos((List<User>) repository.findAll());
    };

    public UserDto getUser(Long id) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");
        return map.UserToUserDto(user);
    }

    public UserDto addUser(UserDto request) throws ParseException {
        User user = map.UserDtoToUser(request);
        user.getAppartements().forEach(app -> {
            app.setUser(user);
        });
        return map.UserToUserDto(repository.save(user));
    }


    public UserDto updateUser(Long id, UserDto request) throws UserNotFound {
        User user = repository.findById(id).orElse(null);
        if(user == null)
            throw new UserNotFound("Utilisateur introuvable!");

        if(request.getAppartements() != null){
            appartementRepository.deleteAll(appartementRepository.findByUserId(id));
        }

        user = map.updateUser(user, request);
        for (Appartement appartement : user.getAppartements()) {
            appartement.setUser(user);
        }
        return map.UserToUserDto(repository.save(user));
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
