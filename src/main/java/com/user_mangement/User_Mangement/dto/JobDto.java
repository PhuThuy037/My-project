package com.user_mangement.User_Mangement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private String job_name;
    private String job_description;
    private Boolean isDone;
}
