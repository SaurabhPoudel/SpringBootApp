package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private Role role;
}
