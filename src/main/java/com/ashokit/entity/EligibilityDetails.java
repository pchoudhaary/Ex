package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="ELIGIBILITY_DTL")
public class EligibilityDetails {
    
	@Id
	///@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="NAME")
	private String name;
	@Column(name="EMAIL")
	private String email;
	@Column(name="GENDER")
	private Character gender;
	@Column(name="MOBILE")
	private Long mobile;
	@Column(name="SSN")
	private String ssn;
	@Column(name="PLAN_NAME")
	private String planName;
	@Column(name="PLAN_STATUS")
	private String planStatus;
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	@Column(name="CREATED_BY")
	private String createBy;
	@Column(name="UPADATED_BY")
 	private String updateBy;
 	@CreationTimestamp
 	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
 	@UpdateTimestamp
 	@Column(name="UPDATED_BY")
 	private LocalDate updatedDate;

	
	
	
	
	
	
	
}
