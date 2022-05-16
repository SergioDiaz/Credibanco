package com.credibanco.assessment.card.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class CardDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	private String cardMasked;
	private String cardHashCode;
	private Integer cardValidationNumber;
	private String cardStatus;
	private String cardType;
	private Date cardCreatedDate;
	private Date cardEnrolledDate;
	private String ownerFirstName;
	private String ownerLastName;
	private Long ownerSsn;
	private String ownerPhone;
	private String ownerEmail;
	private String pan;
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getCardMasked() {
		return cardMasked;
	}
	public void setCardMasked(String cardMasked) {
		this.cardMasked = cardMasked;
	}
	public String getCardHashCode() {
		return cardHashCode;
	}
	public void setCardHashCode(String cardHashCode) {
		this.cardHashCode = cardHashCode;
	}
	public Integer getCardValidationNumber() {
		return cardValidationNumber;
	}
	public void setCardValidationNumber(Integer cardValidationNumber) {
		this.cardValidationNumber = cardValidationNumber;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Date getCardCreatedDate() {
		return cardCreatedDate;
	}
	public void setCardCreatedDate(Date cardCreatedDate) {
		this.cardCreatedDate = cardCreatedDate;
	}
	public Date getCardEnrolledDate() {
		return cardEnrolledDate;
	}
	public void setCardEnrolledDate(Date cardEnrolledDate) {
		this.cardEnrolledDate = cardEnrolledDate;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public Long getOwnerSsn() {
		return ownerSsn;
	}
	public void setOwnerSsn(Long ownerSsn) {
		this.ownerSsn = ownerSsn;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
}
