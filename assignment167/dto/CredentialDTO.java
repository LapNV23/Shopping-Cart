package com.example.assignment167.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CredentialDTO {
    private String accessToken;
    private String refreshToken;
    private List<String> roles;

}