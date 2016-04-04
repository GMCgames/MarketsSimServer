package dal.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import dal.dao.*;

public class UserRepository {

	private DBMock _db;
	public UserRepository() {		
		_db = DBMock.getInstance();
	}
	
	public UserDAO getUserByUsername(String username) {
		return _db.getUserByUsername(username);
	}

	public void insertNewUser(UserDAO user) {
		_db.insertNewUser(user);
	}
}


