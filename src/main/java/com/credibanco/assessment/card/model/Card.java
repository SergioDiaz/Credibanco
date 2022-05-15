package com.credibanco.assessment.card.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "masked", nullable = false)
	private String masked;

	@Column(name = "hash_code", nullable = false , unique = true)
	private String hashCode;

	@Column(name = "validation_number", nullable = false)
	private Integer validationNumber;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "deleted_date")
	private Date deletedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "enrolled_date")
	private Date enrolledDate;

	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "ssn")
	private Long ssn;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@PrePersist
	private void onCreate() {
	    createdDate = new Date();
	    active = false;
	    status = "CREADA";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasked() {
		return masked;
	}

	public void setMasked(String masked) {
		this.masked = masked;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public Integer getValidationNumber() {
		return validationNumber;
	}

	public void setValidationNumber(Integer validationNumber) {
		this.validationNumber = validationNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
