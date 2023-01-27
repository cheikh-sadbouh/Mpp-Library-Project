/*
    =======================================================================================
    This code is part of mpp project.

    ========================================================================================
    Authors :Cheikh Sad Bouh Ahmed Brahim, Emmanuel Coffie Debrah, Kasaija Ronald, 
    ========================================================================================
*/
package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.Address;
import edu.miu.mpp.librarysystem.dao.model.Author;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;
import edu.miu.mpp.librarysystem.dao.model.LibraryMember;
import edu.miu.mpp.librarysystem.dao.model.MaxBookCheckout;
import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.utility.StringPropertyExtractor;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class UserService implements Librarian, Administrator {

    private DataAccessFacade dao;
    private Scanner scanner;

    public UserService() {

        dao = new DataAccessFacade();
        scanner = new Scanner( System.in );
    }


    @Override
    public boolean isMember( String memberId ) {

        return Objects.isNull( dao.getLibraryMember( memberId ) );
    }


    @Override
    public boolean isIsbnExist( String Isbn ) {

        return Objects.isNull( dao.getBook( Isbn ) );
    }


    @Override
    public boolean isBookAvailable( String Isbn ) {

        return Objects.isNull( dao.getBookCopy( Isbn ) );
    }


    @Override
    public String createCheckoutRecord( String Isbn, String memberId ) {

        BookCopy bookCopy = dao.getBookCopy( Isbn );
        bookCopy.changeAvailability();
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(bookCopy);
        LibraryMember member = dao.getLibraryMember( memberId );
        CheckoutRecord checkoutRecord = new CheckoutRecord( memberId );
        checkoutRecord.getEntries().add( checkoutRecordEntry );
        dao.addNewCheckoutRecord( checkoutRecord );
        dao.updateBookCopyAvailability( bookCopy.getBookCopyId().toString() );

        member.getRecord().addAll( Collections.singletonList( checkoutRecord ) );

        dao.updateMember( member );
        return StringPropertyExtractor.findProperties(
                Arrays.asList( "checkoutId", "memberId", "bookCopyId", "checkoutDate", "dueDate" ),
                checkoutRecord.toString() );
    }

    public Optional<CheckoutRecordEntry> getCheckoutRecordEntry(String Isbn, String memberId){
        BookCopy bookCopy = dao.getBookCopy( Isbn );

        List<CheckoutRecord> checkoutRecordList = (List<CheckoutRecord>) dao.getMemberCheckoutRecords(memberId).getData();


        for(CheckoutRecord checkoutRecord: checkoutRecordList){
            for(CheckoutRecordEntry entry: checkoutRecord.getEntries()){
                if (entry.getBookCopy().getBookCopyId().equals(bookCopy.getBookCopyId())){
                    return Optional.of(entry);
                }
            }
        }

        return Optional.empty();
    }

    public Double calculateOverDueBook(String Isbn, String memberId){
        Optional<CheckoutRecordEntry> checkoutRecordEntry = getCheckoutRecordEntry(Isbn, memberId);

        if (checkoutRecordEntry.isPresent()){
            Period period = Period.between(checkoutRecordEntry.get().getCheckoutDate(), LocalDate.now());
            return 0.25 * period.getDays();
        }else{
            return 0.0;
        }
    }


    public Optional<User> authenticateUser( String Id, String password ) {

        HashMap<String, User> users = dao.readUserMap();

        if ( users == null )
            return Optional.empty();

        User user = users.get( Id );

        if ( user == null )
            return Optional.empty();
        else if ( user.getPassword().equals( password ) )
            return Optional.of( user );
        else
            return Optional.empty();
    }


    @Override
    public String getBookCopiesWithCheckoutRecord( String isbn ) {

        StringBuilder responseBuilder = new StringBuilder();
        if ( isIsbnExist( isbn ) ) {
            //does not exist
            return null;
        }
        else {
            Book foundBook = dao.getBook( isbn );

            responseBuilder
                    .append( "book Isbn = " + foundBook.getIsbn() + "\n" )
                    .append( "book Title = " + foundBook.getTitle() + "\n" )
                    .append( "Total Copies = " + foundBook.getBookCopies().size() + "\n" );

            List<BookCopy> bookCopies = foundBook.getBookCopies();
            bookCopies.forEach( bookCopy -> {
                HashMap<String, String> bookCopyCheckoutRecord = getBookCopyCheckoutRecord(
                        bookCopy );

                if ( bookCopyCheckoutRecord.isEmpty() ) {
                    responseBuilder
                            .append( "CopyId =" + bookCopy.getBookCopyId() + "\n" )
                            .append( "CheckoutBy = Available \n" )
                            .append( "Due Date   =  Available\n" );
                }
                else {
                    bookCopyCheckoutRecord.forEach( ( memberName, dueDate ) -> {
                        responseBuilder
                                .append( "CopyId =" + bookCopy.getBookCopyId() + "\n" )
                                .append( "CheckoutBy =" + memberName + "\n" )
                                .append( "Due Date =" + dueDate + "\n" );

                    } );
                }

            } );
            return responseBuilder.toString();
        }

    }


    @Override
    public HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy ) {

        return dao.getBookCopyCheckoutRecord( bookCopy );
    }


    @Override
    public boolean addNewBookCopy( String isbn, String bookCopyId ) {

        Book foundBook = dao.getBook( isbn );
        if ( Objects.nonNull( foundBook ) ) {
            foundBook.getBookCopies().addAll( Collections.singletonList( new BookCopy( UUID
                    .fromString( bookCopyId ), foundBook ) ) );
            dao.addBookCopy( foundBook );
            return true;
        }
        else {
            return false;
        }

    }


    @Override
    public Response addLibraryMember( String memberId, String firstName, String lastName,
            String phone, Address address ) {

        Response response = new Response();

        LibraryMember libraryMember = new LibraryMember( memberId,
                firstName, lastName, phone,
                address );

        dao.saveNewLibraryMember( libraryMember );

        response.setStatus( true );
        response.setData( libraryMember );
        response.setMessage( "Successfully saved user" );

        return response;
    }


    @Override
    public Response getMemberCheckoutRecords( String memberId ) {

        return dao.getMemberCheckoutRecords( memberId );
    }


    @Override
    public boolean addBook( String isbn, String title, MaxBookCheckout maxBookCheckout, List<
            Author> authors, Integer numberOfCopies ) {

        dao.saveNewBook( new Book( isbn, title, maxBookCheckout, authors, numberOfCopies ) );
        return true;
    }


    @Override
    public Response findMemberById( String memberId ) {

        // TODO Auto-generated method stub
        return dao.findMemberById( memberId );
    }
}
