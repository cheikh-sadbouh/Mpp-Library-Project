package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;

import java.util.HashMap;
import java.util.List;


public interface Librarian {
    boolean isMember(String MemberId);

    boolean isIsbnExist(String Isbn);

    boolean isBookAvailable(String Isbn);

    CheckoutRecord createCheckoutRecord(String Isbn, String MemberId);

    String getBookCopiesWithCheckoutRecord(String Isbn);

    HashMap<String, String> getBookCopyCheckoutRecord(BookCopy bookCopy);

    boolean addNewBookCopy(String isbn, String bookCopyId);
}
