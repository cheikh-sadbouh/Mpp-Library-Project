package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.model.*;

import java.util.List;

public interface Administrator {

    boolean addLibraryMember( String memberId, String firstName, String lastName,
            String phone,
            Address address );

    boolean addBook(String isbn, String title, MaxBookCheckout maxBookCheckout,
                    List<Author> authors, Integer numberOfCopies);
}
