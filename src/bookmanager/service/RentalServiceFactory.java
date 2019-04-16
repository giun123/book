package bookmanager.service;

public class RentalServiceFactory {
	public static final RentalService instance = new RentalService();
	
	public static RentalService getInstance() {
		return instance;
	}
	
}
