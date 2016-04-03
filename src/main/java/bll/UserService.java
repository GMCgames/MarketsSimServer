package bll;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import bll.model.MarketsSimUser;
import dal.dao.UserDAO;
import dal.repo.UserRepository;

public class UserService implements UserDetailsService {

	private final UserRepository _userRepository;
	
	public UserService()
	{
		// TODO use dependency injection
		_userRepository = new UserRepository();
	}
	
	@Override
	public final MarketsSimUser loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserDAO userFromDB = _userRepository.getUserByUsername(username);
		if (userFromDB == null) {
            throw new UsernameNotFoundException("user not found");
        }	
		return MapUserDAOToUser(userFromDB);
	}
	
	// -- Private methods ---------------------------------------------
	private MarketsSimUser MapUserDAOToUser(UserDAO userDAO) {
		return new MarketsSimUser(userDAO.getEmail(), userDAO.getUsername(), userDAO.getPassword());
	}
}
