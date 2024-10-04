package com.user_mangement.User_Mangement.service;

import com.user_mangement.User_Mangement.dto.*;
import com.user_mangement.User_Mangement.model.Role;
import com.user_mangement.User_Mangement.model.User;
import com.user_mangement.User_Mangement.repository.RoleRepository;
import com.user_mangement.User_Mangement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagmentService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



    public void register(RegisDto regisDto) {
        Role userRole = roleRepository.findByName(regisDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
            var user =  User.builder()
                    .email(regisDto.getEmail())
                    .username(regisDto.getUsername())
                    .password(passwordEncoder.encode(regisDto.getPassword()))
                    .roles(Set.of(userRole))
                    .build();
            userRepository.save(user);

    }
    public LoginResponse login(LoginDto loginDto) {

        UserDto userDto = new UserDto();
        TokenDto tokenDto = new TokenDto();
        LoginResponse loginResponse = new LoginResponse();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                            loginDto.getPassword()));
            var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            tokenDto.setRefreshToken(refreshToken);
            tokenDto.setToken(jwt);

            userDto.setRoles(user.getRoles());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());

            loginResponse.setTokenDto(tokenDto);
            loginResponse.setUserDto(userDto);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return loginResponse;
    }
    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();

        try{
            User user = userRepository.findById(id).orElseThrow();
            userDto.setEmail(user.getEmail());
            userDto.setRoles(user.getRoles());
            userDto.setUsername(userDto.getUsername());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return userDto;

    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setEmail(user.getEmail());
                    userDto.setUsername(user.getUsername());
                    userDto.setRoles(user.getRoles());
                    return userDto;
                }).collect(Collectors.toList());
    }

}
