package com.upx.ticketsapi.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;

    private String userName;

    private String email;

    private String cpf;

    private String keycloakId;

    private String phoneNumber;

    private LocalDate birthDate;

    private String cep;

    private Boolean active;
}
