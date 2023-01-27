/*
    =======================================================================================
    This code is part of mpp project.

    ========================================================================================
    Authors :Cheikh Sad Bouh Ahmed Brahim, Emmanuel Coffie Debrah, Kasaija Ronald, 
    ========================================================================================
*/
package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;

import java.util.HashMap;

public interface Librarian {

    boolean isMember( String MemberId );


    boolean isIsbnExist( String Isbn );


    String createCheckoutRecord( String Isbn, String MemberId );


    boolean isBookAvailable( String Isbn );


    Response getMemberCheckoutRecords( String memberId );


    Response findMemberById( String memberId );


    String getBookCopiesWithCheckoutRecord( String Isbn );


    HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy );


    boolean addNewBookCopy( String isbn, String bookCopyId );
}
