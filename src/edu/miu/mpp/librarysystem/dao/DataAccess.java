package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.dao.model.*;

import java.util.HashMap;
import java.util.List;


public interface DataAccess {
	User getUser(String username, String pass);
	LibraryMember getLibraryMember(String LibraryMemberId);
	Book getBook(String isbn);
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
}
