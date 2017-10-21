package com.zautomate.modules.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Organization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orgId;
	private String orgType;
	private String orgName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String status;
	private Long statusDate;
	private String parentOrg;
	private String territory;
	private boolean allowAutocheckout = true;
	private String remarks;
}
