package com.example.demo.api.mappers;

import com.example.demo.api.dto.AppartementDto;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.dto.UserMinDto;
import com.example.demo.api.model.Appartement;
import com.example.demo.api.model.User;
import com.example.demo.api.repository.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapStruct {

    MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);
    Appartement AppartementDtoToAppartement(AppartementDto appartementDto);
    UserDto UserToUserDto(User user);

    AppartementDto AppartementToAppartementDto(Appartement appartement);

    User UserDtoToUser(UserDto userDto);

    UserMinDto UserToUserMinDtp(User user);

    User UserMinDtpToUser(UserMinDto userMinDto);

    List<UserMinDto> ListUsersToListUserMinDtos(List<User> users);

    List<UserDto> ListUsersToListUserDtos(List<User> users);

    @Mapping(target = "id", ignore = true)
    User updateUser(@MappingTarget  User user, UserDto userDto);

    @BeforeMapping
    default void deleteChildren(@MappingTarget  User user, UserDto userDto){
        UserRepository userRepository = null;
        if(userDto.getAppartements() != null){
            user.getAppartements().clear();
        }
    }
    
}
