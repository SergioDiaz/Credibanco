package com.credibanco.assessment.card.constant;

public enum CardResponse {
	
	CREATED_SUCCESSFUL("Created successful","00"),
	CREATED_FAILED("Created failed","01"),
	ACTIVATED_SUCCESSFUL("Activated successful","00"),
	NOT_EXISTS("Card does not exists","01"),
	INVALID_VALIDATION_NUMBER("Invalid validation number","02"),
	CARD_ALREADY_ACTIVE("Card already active","03"),
	DELETED_SUCCESSFUL("Deleted successful","00"),
	DELETED_FAILED("Deleted failed","01"),
	CARD_FOUND("Card found","00"),
	CARD_NOT_FOUND("Card not found","01");
	
	private String description;
	private String code;
	
	private CardResponse(String description, String code) {
		this.code = code;
	      this.description = description;
	}
	
	public String getCode() {
	   return code;
	}
	public String getDescription() {
	   return description;
	}
}
