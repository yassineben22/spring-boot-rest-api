package com.example.demo.api.mappers;

import com.example.demo.api.dto.AppartementDto;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.model.Appartement;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapStruct {

    MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);
    Appartement AppartementDtoToAppartement(AppartementDto appartementDto);
    UserDto UserToUserDto(User user);

    AppartementDto AppartementToAppartementDto(Appartement appartement);

    User UserDtoToUser(UserDto userDto);

    List<UserDto> ListUsersToListUserDtos(List<User> users);

    List<AppartementDto> ListAppartementsToListAppartementDtos(List<Appartement> appartements);

    @Mapping(target = "id", ignore = true)
    User updateUser(@MappingTarget  User user, UserDto userDto);

    @Mapping(target = "id", ignore = true)
    Appartement updateAppartement(@MappingTarget  Appartement appartement, AppartementDto appartementDto);
    
}
