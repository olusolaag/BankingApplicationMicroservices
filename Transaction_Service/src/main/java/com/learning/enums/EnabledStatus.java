package com.learning.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnabledStatus {
	DISABLED(false),	ENABLED(true);
	
	public final Boolean value;
	@JsonValue
	public boolean value() {
		return this.value;
	}
	private EnabledStatus(Boolean value) {
		this.value=value;
	}
	@JsonCreator
	public static EnabledStatus of(boolean b) {
		return b ? ENABLED : DISABLED;
	}
}
