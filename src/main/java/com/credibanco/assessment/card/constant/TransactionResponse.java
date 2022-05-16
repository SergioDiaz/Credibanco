package com.credibanco.assessment.card.constant;

public enum TransactionResponse {
	
	TRANSACTION_SUCCESSFUL("Transaction sccessful","00"),
	NOT_EXISTS("Card does not exists","01"),
	NOT_ACTIVE("Card not yet acive","02"),
	CANCEL_SUCCESSFUL("Transaction canceled successfully","00"),
	INVALID_REFERENCE_NUMBER("Invalid refernce number","01"),
	INVALID_CANCEL_TRANSACTION("Invalid cancel transaction","02");
	
	private String description;
	private String code;
	
	private TransactionResponse(String description, String code) {
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
