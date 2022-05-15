package com.credibanco.assessment.card.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CardTransactionsDTO {

	private Long trasactionId;
	private Integer transactionAmount;
	private String transactionReference;
	private String transactionAddress;
	private Date transactionDateTime;
	private String cardMaskedPan;
	private String cardHashCode;
	private String ownerName;
	public Long getTrasactionId() {
		return trasactionId;
	}
	public void setTrasactionId(Long trasactionId) {
		this.trasactionId = trasactionId;
	}
	public Integer getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getTransactionAddress() {
		return transactionAddress;
	}
	public void setTransactionAddress(String transactionAddress) {
		this.transactionAddress = transactionAddress;
	}
	public Date getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getCardMaskedPan() {
		return cardMaskedPan;
	}
	public void setCardMaskedPan(String cardMaskedPan) {
		this.cardMaskedPan = cardMaskedPan;
	}
	public String getCardHashCode() {
		return cardHashCode;
	}
	public void setCardHashCode(String cardHashCode) {
		this.cardHashCode = cardHashCode;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
}
