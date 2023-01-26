package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.dao.model.*;

import java.util.HashMap;
import java.util.List;

public interface DataAccess {
	User getUser(String username, String pass);
	LibraryMember getLibraryMember(String LibraryMemberId);
	Book getBook(String isbn);
	boolean updateBookCopyAvailability(String bookCopyId);
	boolean updateMember(LibraryMember libraryMember);
	BookCopy getBookCopy(String isbn);
	List<Book> getAllBook();
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readLibraryMemberMap();
	public void saveNewLibraryMember(LibraryMember LibraryMember);
	public void saveNewBook(Book book);
	public void addBookCopy(Book book);
	public void addNewCheckoutRecord(CheckoutRecord record);
	HashMap<String,String> getBookCopyCheckoutRecord(BookCopy bookCopy);

    User getUser( String username, String pass );


    LibraryMember getLibraryMember( String LibraryMemberId );


    Book getBook( String isbn );


    boolean updateBookCopyAvailability( String bookCopyId );


    BookCopy getBookCopy( String isbn );


    List<Book> getAllBook();


    List<CheckoutRecord> getCheckoutRecords( String memberId );


    public HashMap<String, Book> readBooksMap();


    public HashMap<String, CheckoutRecord> readCheckoutRecordMap();


    public HashMap<String, User> readUserMap();


    public HashMap<String, LibraryMember> readLibraryMemberMap();


    public void saveNewLibraryMember( LibraryMember LibraryMember );


    public void saveNewBook( Book book );


    public void addBookCopy( Book book );


    public void addNewCheckoutRecord( CheckoutRecord record );


    HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy );

}
