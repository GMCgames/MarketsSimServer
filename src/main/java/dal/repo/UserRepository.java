package dal.repo;

import java.util.ArrayList;
import java.util.List;

import dal.dao.*;

public class UserRepository {
	
	private List<UserDAO> _userTableMock;
	
	public UserRepository() {		
		// Initialise the DB mock
		InitialiseDBMock();
	}
	
	public UserDAO getUserByEmail(String email) {
		for (UserDAO u : _userTableMock) {
			if (email.equals(u.getEmail())) {
				return u;
			}
		}
		return null;
	}

	private void InitialiseDBMock(){
		// Initialise Users table mock with 2 only valid users
		_userTableMock = new ArrayList<UserDAO>();
		_userTableMock.add(new UserDAO("user1@gmail.com", "12345", "user1"));
		_userTableMock.add(new UserDAO("user2@gmail.com", "67890", "user2"));
	}
}


