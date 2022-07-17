package com.example.assignment167.entiy.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CredentialDto {
    private String accessToken;
    private String refreshToken;
}
