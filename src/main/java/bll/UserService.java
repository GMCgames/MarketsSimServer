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
		return mapUserDAOToMarketsSimUser(userFromDB);
	}
	
	public final void registerNewUser(MarketsSimUser user) {
		_userRepository.insertNewUser(mapMarketsSimUserToUserDAO(user));
	}
	// -- Private methods ---------------------------------------------
	private MarketsSimUser mapUserDAOToMarketsSimUser(UserDAO userDAO) {
		return new MarketsSimUser(userDAO.getEmail(), userDAO.getUsername(), userDAO.getPassword());
	}
	
	private UserDAO mapMarketsSimUserToUserDAO(MarketsSimUser user) {
		return new UserDAO(user.getEmail(), user.getUsername(), user.getPassword());
	}
}
