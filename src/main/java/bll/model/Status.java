package bll.model;

/**
 * Entity that holds the application and its dependencies health values.
 */
public class Status {
	private String _app;
	private String _db;
	
	public Status() {
		_app = "OK";
		_db = "OK";
	}
	public String getApp() {
		return _app;
	}
	
	public String getDb() {
		return _db;
	}
	
}
