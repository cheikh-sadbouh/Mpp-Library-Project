package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.Address;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.BookCopy;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;
import edu.miu.mpp.librarysystem.dao.model.LibraryMember;
import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.utility.StringPropertyExtractor;

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
        BookCopy bookCopy = dao.getBookCopy(Isbn);
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry( dao.getBookCopy(Isbn));
        LibraryMember member =  dao.getLibraryMember( memberId );
        CheckoutRecord checkoutRecord = new CheckoutRecord( memberId);
        checkoutRecord.getEntries().add( checkoutRecordEntry );
        dao.addNewCheckoutRecord( checkoutRecord );
        dao.updateBookCopyAvailability(bookCopy.getBookCopyId().toString());

            member.getRecord().addAll(Collections.singletonList(checkoutRecord));


        dao.updateMember(member);
        return StringPropertyExtractor.findProperties(
                Arrays.asList("checkoutId","memberId","bookCopyId","checkoutDate","dueDate"),
                checkoutRecord.toString());
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
        if ( !isIsbnExist( isbn ) ) {
            System.out.println( isbn + " there is no book match this ISBN" );
            return null;
        }
        else {
            Book foundBook = dao.getBook( isbn );

            responseBuilder.append( "book Isbn | book Title | Total Copies" );
            responseBuilder.append( foundBook.getIsbn() + " | " + foundBook.getTitle() + " | "
                    + foundBook.getBookCopies().size() );
            if ( !foundBook.getBookCopies().isEmpty() )
                responseBuilder.append( "CopyId | CheckoutBy |  Due Date" );

            List<BookCopy> bookCopies = foundBook.getBookCopies();
            bookCopies.forEach( bookCopy -> {
                HashMap<String, String> bookCopyCheckoutRecord = getBookCopyCheckoutRecord(
                        bookCopy );
                String[] recordValues = Optional.ofNullable( ( String[] )bookCopyCheckoutRecord
                        .values().toArray() ).orElseGet( null );
                responseBuilder.append( bookCopy.getBookCopyId() + " | " + ( Objects.isNull(
                        recordValues[0] ) ? "non" : recordValues[0] ) + " | " + ( Objects.isNull(
                                recordValues[1] ) ? "non" : recordValues[1] ) );

            } );
        }

        return responseBuilder.toString();

    }


    @Override
    public HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy ) {

        return dao.getBookCopyCheckoutRecord( bookCopy );
    }


    @Override
    public boolean addNewBookCopy( String isbn, String bookCopyId ) {

        Book foundBook = dao.getBook( isbn );
        if ( Objects.nonNull( foundBook ) ) {
            foundBook.getBookCopies().add( new BookCopy( UUID.fromString( bookCopyId ),
                    foundBook ) );
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
    public boolean addBook(String isbn, String title, MaxBookCheckout maxBookCheckout, List<Author> authors, Integer numberOfCopies) {
        dao.saveNewBook(new Book(isbn,title,maxBookCheckout, authors, numberOfCopies));
        return true;
    }
}
