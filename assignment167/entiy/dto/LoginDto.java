package com.example.assignment167.entiy.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class LoginDto {
    private String username;
    private String password;
}
