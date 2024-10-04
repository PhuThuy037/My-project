package com.user_mangement.User_Mangement.dto;

import com.user_mangement.User_Mangement.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDto {

    private String email;
    private String username;
    private Set<Role> roles;
}
