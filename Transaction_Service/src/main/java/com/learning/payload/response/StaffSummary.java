package com.learning.payload.response;
//Use Case GET /api/admin/staff

import com.learning.enums.EnabledStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffSummary {
	private long staffId;
	private String staffName;
	private EnabledStatus status;
}
