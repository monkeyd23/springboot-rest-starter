package com.zautomate.modules.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zautomate.commons.enums.Strings;
import com.zautomate.modules.organization.Organization;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
	private String comments;
	private String activationCode;
	private String emailnotification;
	private String forcePswdChange;
	@ManyToOne
	@JoinColumn(name = "organization")
	private Organization organization;
	private String deviceId;
	private String thirdPartyName;
	private String notificationStatus;
	private String mailVerificationStatus;
	private String frequencySpecification;
	private String frequency;
	private String isDefault;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

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
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status.equalsIgnoreCase(Strings.USER_STATE_ACTIVE.toString());
	}
}
