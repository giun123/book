package bookmanager.service;

public class BookServiceFactory {
	public static final BookService instance = new BookService();
	
	public static BookService getInstance() {
		return instance;
	}
	
}
