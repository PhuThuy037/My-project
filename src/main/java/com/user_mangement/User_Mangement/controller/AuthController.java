package com.user_mangement.User_Mangement.controller;

import com.user_mangement.User_Mangement.dto.LoginDto;
import com.user_mangement.User_Mangement.dto.LoginResponse;
import com.user_mangement.User_Mangement.dto.RegisDto;
import com.user_mangement.User_Mangement.dto.UserDto;
import com.user_mangement.User_Mangement.model.Role;
import com.user_mangement.User_Mangement.service.UserManagmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserManagmentService userManagmentService;


    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisDto regisDto) {
        userManagmentService.register(regisDto);
        return ResponseEntity.ok(regisDto);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userManagmentService.login(loginDto));
    }
    @GetMapping("/admin/get-all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userManagmentService.findAllUsers());
    }
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable Long id) {
        return ResponseEntity.ok(userManagmentService.getUserById(id));
    }
    @GetMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userManagmentService.deleteUserById(id);
    }
}
