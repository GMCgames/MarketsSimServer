package bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dal.dao.UserDAO;
import dal.repo.UserRepository;

public class UserService implements UserDetailsService {

	private final String ROLE_USER = "ROLE_USER";
	private final UserRepository _userRepository;
	
	public UserService()
	{
		// TODO use dependency injection
		_userRepository = new UserRepository();
	}
	
	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserDAO userFromDB = _userRepository.getUserByEmail(username);
		if (userFromDB == null) {
            throw new UsernameNotFoundException("user not found");
        }	
		return MapUserDAOToUser(userFromDB);
	}
	
	// -- Private methods ---------------------------------------------
	private User MapUserDAOToUser(UserDAO userDAO) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
		return new User(userDAO.getEmail(), userDAO.getPassword(), grantedAuthorities);
	}
}
