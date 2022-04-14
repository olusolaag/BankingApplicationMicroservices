package com.cogent.customer_service.payload.request;

import com.cogent.customer_service.enums.EnabledStatus;

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
