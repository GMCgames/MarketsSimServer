package dal.dao;

public class UserDAO {
	private String _email;
	private String _password;
	private String _nick;
	
	public UserDAO(String email, String password, String nick) {
		_email = email;
		_password = password;
		_nick = nick;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public String getNick() {
		return _nick;
	}
}
