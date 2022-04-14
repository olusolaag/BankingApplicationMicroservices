package com.learning.payload.request;

import com.learning.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetEnabledRequest {
	private Long id;
	private EnabledStatus status;
}
