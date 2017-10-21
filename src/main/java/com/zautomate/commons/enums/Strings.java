package com.zautomate.commons.enums;

public enum Strings {
	
	// User states
	USER_STATE_ACTIVE("Active"),
	USER_STATE_INACTIVE("Inactive");
	
	private final String text;
	
	Strings(final String text) {
        this.text = text;
    }
	
	@Override
    public String toString() {
        return text;
    }
}
