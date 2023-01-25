package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;

public interface Administrator {
   boolean addNewBookCopy(String isbn,String bookCopyId);

}
