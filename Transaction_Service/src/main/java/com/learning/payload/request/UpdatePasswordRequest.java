package com.learning.payload.request;
//Use Case PUT /api/customer/:username/forgot
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
	private String newPassword;
}
