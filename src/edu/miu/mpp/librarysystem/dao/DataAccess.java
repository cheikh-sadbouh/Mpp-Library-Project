package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.dao.model.*;

import java.util.HashMap;
import java.util.List;


public interface DataAccess {
	User getUser(String username, String pass);
	Member getMember(String memberId);
	Book getBook(String isbn);
	BookCopy getBookCopy(String isbn);
	List<Book> getAllBook();
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book);
	public void addNewCheckoutRecord(CheckoutRecord record);
}
