package com.learning.payload.request;
//Use Case POST /api/admin/staff

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffRequest {
	@NotBlank
	private String staffFullName;
	@NotBlank
	private String staffUserName;
	@NotBlank
	private String staffPassword;
}
