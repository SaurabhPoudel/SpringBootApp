package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.UserDTO;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface UserService {
    String loginWithJwt(String username, String password);
    UserDTO register(UserDTO userDTO);
    Optional<UserDTO> findByUsername(String username);
    Optional<UserDTO> login(String username, String password);
}
//POST /api/auth/register – register with username & password
//
//POST /api/auth/login – login with credentials
//// POST /api/auth/register
//{
//  "username": "admin",
//  "password": "admin123"
//}
// POST /api/auth/login
/*{
        "username": "admin",
        "password": "admin123"
        }
*/