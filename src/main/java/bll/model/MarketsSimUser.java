package bll.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MarketsSimUser implements UserDetails {

	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String username;
	
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean enabled;
	private List<GrantedAuthority> grantedAuthorities;
	
	private String ROLE_USER = "ROLE_USER";
	
	public MarketsSimUser(){	
	}
	
	public MarketsSimUser(String _email, String _username, String _password) {
		email = _email;
		username = _username;
		password = _password;
		
		grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
		accountExpired = false;
		accountLocked = false;
		credentialsExpired = false;
		enabled = true;		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		password = _password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String _username) {
		username = _username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String _email) {
		email = _email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
