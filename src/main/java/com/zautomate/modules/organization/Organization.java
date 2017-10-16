package com.zautomate.modules.organization;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Organization {
	
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
	@Lob
	private String remarks;
	
	// No orgs constructor to be used by spring and data-jpa
	public Organization() {}
	
	public Organization(String orgName, boolean allowAutocheckout, String city, String email, String address, String zip, String phone, String remarks, String state, String status, Long statusDate, String orgType) {
		  this.orgType=orgType;
		  this.orgName=orgName;
		  this.phone=phone;
		  this.email=email;
		  this.address=address;
		  this.city=city;
		  this.state=state;
		  this.remarks = remarks;
		  this.zip=zip;
		  this.status=status;
		  this.statusDate=statusDate;
		  this.allowAutocheckout =allowAutocheckout;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Long statusDate) {
		this.statusDate = statusDate;
	}

	public String getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public boolean isAllowAutocheckout() {
		return allowAutocheckout;
	}

	public void setAllowAutocheckout(boolean allowAutocheckout) {
		this.allowAutocheckout = allowAutocheckout;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
