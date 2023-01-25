package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;


public interface Librarian {
    boolean isMember(String MemberId);
    boolean isIsbnExist(String Isbn);
    boolean isBookAvailable(String Isbn);
    CheckoutRecord createCheckoutRecord(String Isbn,String MemberId);

}
