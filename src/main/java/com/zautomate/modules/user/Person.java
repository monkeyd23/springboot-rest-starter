package com.zautomate.modules.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zautomate.commons.enums.Strings;
import com.zautomate.modules.organization.Organization;

@Entity
public class Person implements UserDetails {
	
	private static final long serialVersionUID = 8235278201303493888L;
	
	@Id
	private String username;
	private String fullname;
	@JsonIgnore
	private String password;
	private String role;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String status;
	private Long statusDate;
	@Lob
	private String comments;
	private String activationCode;
	private String emailnotification;
	private String forcePswdChange;
	@ManyToOne
	private Organization organization;
	private String deviceId;
	private String thirdPartyName;
	private String notificationStatus;
	private String mailVerificationStatus;
	private String frequencySpecification;
	private String frequency;
	private String isDefault;
	
	public Person () {}
	
	public Person(String username, String fullname, String password, String role, String email, String phone,
				  String address, String city, String state, String zip, String status, Long statusDate, String comments,
				  String activationCode, String emailnotification, String forcePswdChange, Organization organization,
				  String deviceId) {
		super();
		this.username = username.equals("")? null :username;
		this.fullname = fullname.equals("")? null :fullname;
		this.password = password.equals("")? null :password;
		this.role = role.equals("")? null :role;
		this.email = email.equals("")? null :email;
		this.phone = phone.equals("")? null :phone;
		this.address = address.equals("")? null :address;
		this.city = city.equals("")? null :city;
		this.state = state.equals("")? null :state;
		this.zip = zip.equals("")? null :zip;
		this.status = status.equals("")? null :status;
		this.statusDate = statusDate;
		this.comments = null;
		this.activationCode = activationCode.equals("")? null :activationCode;
		this.emailnotification = emailnotification.equals("")? null :emailnotification;
		this.forcePswdChange = forcePswdChange.equals("")? null :forcePswdChange;
		this.organization = organization;
		this.deviceId = deviceId.equals("")? null :deviceId;
		//this.thirdParty = thirdParty;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public String getEmailnotification() {
		return emailnotification;
	}
	public void setEmailnotification(String emailnotification) {
		this.emailnotification = emailnotification;
	}
	public String getForcePswdChange() {
		return forcePswdChange;
	}
	public void setForcePswdChange(String forcePswdChange) {
		this.forcePswdChange = forcePswdChange;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getThirdPartyName() {
		return thirdPartyName;
	}
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	public String getNotificationStatus() {
		return notificationStatus;
	}
	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
	public String getMailVerificationStatus() {
		return mailVerificationStatus;
	}
	public void setMailVerificationStatus(String mailVerificationStatus) {
		this.mailVerificationStatus = mailVerificationStatus;
	}
	public String getFrequencySpecification() {
		return frequencySpecification;
	}
	public void setFrequencySpecification(String frequencySpecification) {
		this.frequencySpecification = frequencySpecification;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : this.role.split(","))
			authorities.add(new SimpleGrantedAuthority(role));
		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// accounts never expire
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// we never lock accounts
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// credentials never expire
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.status.equalsIgnoreCase(Strings.USER_STATE_ACTIVE.toString());
	}
}
