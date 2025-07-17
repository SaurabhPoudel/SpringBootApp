package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
         UserDTO dto = new UserDTO();
         dto.setUsername(user.getUsername());
         dto.setPassword(user.getPassword());
    return dto;
    }
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
