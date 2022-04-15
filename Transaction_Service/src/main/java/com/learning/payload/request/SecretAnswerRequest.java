package com.learning.payload.request;
//Use Case GET /api/customer/:username/forgot/question/answer
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretAnswerRequest {
	@NotBlank
	private String answer;
}
