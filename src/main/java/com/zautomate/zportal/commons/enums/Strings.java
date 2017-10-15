package com.zautomate.zportal.commons.enums;

public enum Strings {
	
	// User states
	USER_STATE_ACTIVE("Active"),
	USER_STATE_INACTIVE("Inactive");
	
	private final String text;
	
	private Strings(final String text) {
        this.text = text;
    }
	
	@Override
    public String toString() {
        return text;
    }
}
