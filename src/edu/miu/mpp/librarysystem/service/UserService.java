package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;

import java.util.*;


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
    public String getBookCopiesWithCheckoutRecord(String isbn) {

        StringBuilder responseBuilder = new StringBuilder();
          if(!isIsbnExist(isbn)) {
              System.out.println(isbn+" there is no book match this ISBN");
              return  null;
          }else{
              Book foundBook =  dao.getBook(isbn);

              responseBuilder.append("book Isbn | book Title | Total Copies");
              responseBuilder.append(foundBook.getIsbn()+" | "+foundBook.getTitle()+" | "+foundBook.getBookCopies().size());
              if(!foundBook.getBookCopies().isEmpty()) responseBuilder.append("CopyId | CheckoutBy |  Due Date");

            List<BookCopy> bookCopies = foundBook.getBookCopies();
                    bookCopies.forEach(bookCopy -> {
                        HashMap<String, String> bookCopyCheckoutRecord = getBookCopyCheckoutRecord(bookCopy);
                        String[] recordValues = Optional.ofNullable( (String[]) bookCopyCheckoutRecord.values().toArray()).orElseGet(null);
                        responseBuilder.append(bookCopy.getBookCopyId()+" | "+(Objects.isNull(recordValues[0])?"non":recordValues[0])+" | "+(Objects.isNull(recordValues[1])?"non":recordValues[1]));

                    });
          }

          return  responseBuilder.toString();

    }

    @Override
    public HashMap<String, String> getBookCopyCheckoutRecord(BookCopy bookCopy) {
        return dao.getBookCopyCheckoutRecord(bookCopy);
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
