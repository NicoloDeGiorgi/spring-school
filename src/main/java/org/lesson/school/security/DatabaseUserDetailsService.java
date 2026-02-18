package org.lesson.school.security;

import java.util.Optional;

import org.lesson.school.model.User;
import org.lesson.school.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class DatabaseUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//	Cerca nel database se esiste un utente che abbia lo username richiesto 
		Optional<User> user = userRepository.findByUsername(username);
		// se lo trova, allora..
		if (user.isPresent()) {
		// costruisce una nuova istanza di DatabaseUserDetails che riguardi l'utente con l'username inserito	
			return new DatabaseUserDetails(user.get());
			
			// in casi cibtrario lancia una nuova eccezzione di tipo
		} else {
			throw new UsernameNotFoundException("Username" + username + "not found!");
		}
	}
}