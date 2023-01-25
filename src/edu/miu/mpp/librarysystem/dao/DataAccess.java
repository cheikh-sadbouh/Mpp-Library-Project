package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.LibraryMember;
import edu.miu.mpp.librarysystem.dao.model.User;

import java.util.HashMap;

//import business.Book;
//import business.LibraryMember;
//import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}
