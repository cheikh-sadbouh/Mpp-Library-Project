package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


public class UserService implements Librarian ,Administrator{
    private DataAccessFacade dao = new DataAccessFacade();
    @Override
    public boolean isMember(String memberId) {
        return Objects.isNull(dao.getLibraryMember(memberId));
    }

    @Override
    public boolean isIsbnExist(String Isbn) {
        return Objects.isNull(dao.getBook(Isbn));
    }

    @Override
    public boolean isBookAvailable(String Isbn) {
        return Objects.isNull(dao.getBookCopy(Isbn));
    }

    @Override
    public CheckoutRecord createCheckoutRecord(String Isbn, String memberId) {
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(dao.getBookCopy(Isbn));
        CheckoutRecord checkoutRecord = new CheckoutRecord(dao.getLibraryMember(memberId));
        checkoutRecord.getEntries().add(checkoutRecordEntry);
        dao.addNewCheckoutRecord(checkoutRecord);
        return checkoutRecord;
    }




    @Override
    public boolean addNewBookCopy(String isbn,String bookCopyId) {
        Book foundBook= dao.getBook(isbn);
        if(Objects.nonNull(foundBook)){
              foundBook.getBookCopies().add(new BookCopy(UUID.fromString(bookCopyId),foundBook));
               dao.addBookCopy(foundBook);
            System.out.println("new bookCopy has been added successfully");
            return  true;
        }else{
            System.out.println("book was not found");

            return false;
        }

    }
}
