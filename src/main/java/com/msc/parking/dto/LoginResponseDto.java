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
@Schema(description = "Response DTO for user login")
public class LoginResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Registration ID", example = "12345")
    private Integer regId;

    @Schema(description = "Response message", example = "Login realizado com sucesso", required = true)
    private String message;

    @Schema(description = "Role name", example = "Admin")
    private String roleName;
}