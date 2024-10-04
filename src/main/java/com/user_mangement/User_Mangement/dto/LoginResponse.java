package com.user_mangement.User_Mangement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponse {
    private UserDto userDto;
    private TokenDto tokenDto;
}
