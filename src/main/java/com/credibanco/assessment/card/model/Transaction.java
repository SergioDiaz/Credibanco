package com.credibanco.assessment.card.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ref_number")
	private String refNumer;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "address")
	private String address;

	@Column(name = "trn_date_time")
	private Date trnDateTime;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "card_id")
	private Card card;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefNumer() {
		return refNumer;
	}

	public void setRefNumer(String refNumer) {
		this.refNumer = refNumer;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTrnDateTime() {
		return trnDateTime;
	}

	public void setTrnDateTime(Date trnDateTime) {
		this.trnDateTime = trnDateTime;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
