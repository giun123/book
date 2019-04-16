package bookmanager.service;

public class UserServiceFactory {
	public static final UserService instance = new UserService();
	
	public static UserService getInstance() {
		return instance;
	}
	
}
