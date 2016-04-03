package dal.dao;

public class UserDAO {
	private String _email;
	private String _password;
	private String _username;
	
	public UserDAO(String email, String username, String password) {
		_email = email;
		_username = username;
		_password = password;		
	}
	
	public String getEmail() {
		return _email;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public String getUsername() {
		return _username;
	}
}
