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
@Schema(description = "Request DTO for user registration")
public class UserRegistrationRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "User name", example = "John Doe", required = true)
	private String userName;

	@Schema(description = "Mobile number of the user", example = "1234567890", required = true)
	private String mobileNumber;

	@Schema(description = "Password of the user", example = "password123", required = true)
	private String password;

	@Schema(description = "Role ID of the user", example = "1", required = true)
	private Integer roleId;

	@Schema(description = "Overall experience of the user", example = "5.0", required = true)
	private Float overAllExperience;

	@Schema(description = "MSC experience of the user", example = "3.0", required = true)
	private Float mscExperience;
}