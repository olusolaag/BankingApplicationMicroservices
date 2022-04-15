package com.learning.payload.request;
//Use Case PUT /api/staff/customer
//Use Case PUT /api/admin/staff

import com.learning.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateRequest {
	private long id;
	private EnabledStatus status;
}
