package org.lesson.school.security;

import java.util.Collection;
import java.util.HashSet;
import org.lesson.school.model.Role;
import org.lesson.school.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {
	private final Integer id;
	private final String username;
	private final String password;
	private final HashSet<GrantedAuthority> authorities;

	public DatabaseUserDetails(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		authorities = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities; // qui restituiamo le autorità reali
	}

	@Override
	public String getPassword() {
	    return password; // qui restituiamo la password reale dal costruttore
	}

	@Override
	public String getUsername() {
	    return username; // qui restituiamo lo username reale
	}

}
