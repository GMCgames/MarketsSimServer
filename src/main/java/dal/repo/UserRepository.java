package dal.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import dal.dao.*;

public class UserRepository {
	
	private List<UserDAO> _userTableMock;
	
	public UserRepository() {		
		// Initialise DB mock
		InitialiseDBMock();
	}
	
	public UserDAO getUserByUsername(String username) {
		for (UserDAO u : _userTableMock) {
			if (username.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}

	private void InitialiseDBMock(){
		// Initialise Users table mock with only 2 valid users
		_userTableMock = new ArrayList<UserDAO>();
		
		_userTableMock.add(new UserDAO("user1@gmail.com", "user1",
				getEncodedPassword("user1", "12345")));
		_userTableMock.add(new UserDAO("user2@gmail.com", "user2",
				getEncodedPassword("user2", "67890")));
	}
	
	private String getEncodedPassword(String username, String rawPass) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String encPass = passwordEncoder.encodePassword(rawPass, username);
		return encPass;
	}
}


