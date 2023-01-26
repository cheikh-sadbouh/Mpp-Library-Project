package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.model.Address;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.LibraryMember;

public interface Administrator {

    boolean addLibraryMember( String memberId, String firstName, String lastName,
            String phone,
            Address address );
}
