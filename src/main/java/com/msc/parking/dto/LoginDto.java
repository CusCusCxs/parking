package com.msc.parking.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for user login")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Mobile number of the user", example = "1234567890", required = true)
    private String mobileNo;

    @Schema(description = "Password of the user", example = "password123", required = true)
    private String password;
}