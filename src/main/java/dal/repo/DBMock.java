package dal.repo;

import java.util.ArrayList;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import dal.dao.UserDAO;

/* Mock the DB for development purposes */
public class DBMock {

	private static DBMock instance = null;
	private static ArrayList<UserDAO> _userTableMock;
	protected DBMock() {
		InitialiseDBMock();
	}
	
	public static DBMock getInstance() {
		if (instance == null) {
			instance = new DBMock();
		}
		return instance;
	}
	
	public UserDAO getUserByUsername(String username) {
		for (UserDAO u : _userTableMock) {
			if (username.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}
	
	public UserDAO getUserByEmail(String email) {
		for (UserDAO u : _userTableMock) {
			if (email.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}
	
	public void insertNewUser(UserDAO user) {
		_userTableMock.add(new UserDAO(user.getEmail(), user.getUsername(),
				getEncodedPassword(user.getUsername(), user.getPassword())));
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
