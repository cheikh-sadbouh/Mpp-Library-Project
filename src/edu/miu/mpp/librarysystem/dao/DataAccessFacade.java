package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.model.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataAccessFacade implements DataAccess {

    private static final long serialVersionUID = 5399827794066637059L;

    enum StorageType {
        BOOKS, MEMBERS, USERS, CHECKOUT_RECORD;
    }

    public static final String OUTPUT_DIR = System.getProperty( "user.dir" )
            + File.separator + "src" + File.separator
            + "edu" + File.separator
            + "miu" + File.separator
            + "mpp" + File.separator
            + "librarysystem" + File.separator
            + "dao" + File.separator
            + "storage";

    public static final String DATE_PATTERN = "MM/dd/yyyy";

    static void loadBookMap( List<Book> bookList ) {

        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach( book -> books.put( book.getIsbn(), book ) );
        saveToStorage( StorageType.BOOKS, books );
    }


    static void loadCheckoutRecordMap() {

        HashMap<String, CheckoutRecord> checkoutRecordMap = new HashMap<>();
        saveToStorage( StorageType.CHECKOUT_RECORD, checkoutRecordMap );
    }


    static void loadUserMap( List<User> userList ) {

        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach( user -> users.put( user.getId(), user ) );
        saveToStorage( StorageType.USERS, users );
    }


    static void loadMemberMap( List<LibraryMember> memberList ) {

        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach( member -> members.put( member.getMemberId(), member ) );
        saveToStorage( StorageType.MEMBERS, members );
    }


    static void saveToStorage( StorageType type, Object ob ) {

        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath( OUTPUT_DIR, type.toString() );
            out = new ObjectOutputStream( Files.newOutputStream( path ) );
            out.writeObject( ob );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        finally {
            if ( out != null ) {
                try {
                    out.close();
                }
                catch ( Exception e ) {
                }
            }
        }
    }


    @Override
    public void saveNewLibraryMember( LibraryMember member ) {

        HashMap<String, LibraryMember> mems = readLibraryMemberMap();
        String memberId = member.getMemberId();
        mems.put( memberId, member );
        saveToStorage( StorageType.MEMBERS, mems );
    }


    @Override
    public void saveNewBook( Book book ) {

        HashMap<String, Book> bookMap = readBooksMap();
        String isbn = book.getIsbn();
        bookMap.put( isbn, book );
        saveToStorage( StorageType.BOOKS, bookMap );
    }


    @Override
    public void addBookCopy( Book book ) {

        HashMap<String, Book> bookMap = readBooksMap();
        String isbn = book.getIsbn();
        bookMap.replace( isbn, book );
        saveToStorage( StorageType.BOOKS, bookMap );
    }


    @Override
    public void addNewCheckoutRecord( CheckoutRecord record ) {

        HashMap<String, CheckoutRecord> checkoutRecordMap = readCheckoutRecordMap();
        checkoutRecordMap.put( record.getCheckoutId(), record );
        saveToStorage( StorageType.CHECKOUT_RECORD, checkoutRecordMap );
    }


    @Override
    public HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy ) {

        HashMap<String, String> foundBookCopyCheckoutRecord = new HashMap<>();
        HashMap<String, CheckoutRecord> checkoutRecordMap = readCheckoutRecordMap();
        checkoutRecordMap.forEach( ( key, record ) -> record.getEntries().forEach( entry -> {
            if ( entry.getBookCopy().getBookCopyId().equals( bookCopy.getBookCopyId() ) ) {
                LibraryMember member = getLibraryMember( record.getLibraryMemberId() );
                foundBookCopyCheckoutRecord.put(member.getFirstName()
                                .concat( " " )
                                .concat( member.getLastName().concat("( id ="+member.getMemberId()+" )") ), entry.getDueDate().toString() );

            }
        } ) );

        return foundBookCopyCheckoutRecord;
    }


    @Override
    public User getUser( String username, String pass ) {

        try {
            HashMap<String, User> users = ( HashMap<String, User> )readFromStorage(
                    StorageType.USERS );
            if ( users.containsKey( username ) ) {
                var u = users.get( username );
                if ( u.getPassword().equals( pass ) ) {
                    return u;
                }
            }

        }
        catch ( Exception e ) {
            return null;
        }

        return null;

    }


    @Override
    public LibraryMember getLibraryMember( String LibraryMemberId ) {

        HashMap<String, LibraryMember> members = ( HashMap<String, LibraryMember> )readFromStorage(
                StorageType.MEMBERS );
        if ( members.containsKey( LibraryMemberId ) ) {
            return members.get( LibraryMemberId );
        }
        return null;
    }


    @Override
    public Book getBook( String isbn ) {

        HashMap<String, Book> books = ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
        if ( books.containsKey( isbn ) ) {
            return books.get( isbn );
        }
        return null;
    }


    @Override
    public boolean updateBookCopyAvailability( String bookCopyId ) {

        HashMap<String, Book> books = ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
        for ( Book b : books.values() ) {
            for ( BookCopy bc : b.getBookCopies() ) {
                if ( bc.getBookCopyId().toString().equals( bookCopyId ) ) {
                    bc.setAvailable(false);
                    books.replace(b.getIsbn(),b);
                    saveToStorage( StorageType.BOOKS, books );
                    return true;
                }
            }

        }
        return false;
    }


    @Override
    public boolean updateMember( LibraryMember libraryMember ) {

        HashMap<String, LibraryMember> memberHashMap = readLibraryMemberMap();
        String memberId = libraryMember.getMemberId();
        if ( Objects.isNull( memberHashMap.replace( memberId, libraryMember ) ) )
            return false;
        saveToStorage( StorageType.MEMBERS, memberHashMap );
        return true;
    }


    @Override
    public BookCopy getBookCopy( String isbn ) {

        HashMap<String, Book> books = ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
        for ( Book b : books.values() ) {
            if ( b.getIsbn() != null && b.getIsbn().equals( isbn.trim() ) ) {
                for ( BookCopy bc : b.getBookCopies() ) {
                    if ( bc.isAvailable() ) {
                        return bc;
                    }
                }
            }
        }

        return null;
    }


    @Override
    public List<Book> getAllBook() {

        HashMap<String, Book> allBookMap = ( HashMap<String, Book> )readFromStorage(
                StorageType.BOOKS );
        List<Book> books = allBookMap.values()
                .stream()
                .collect( Collectors.toList() );
        return books;
    }

    /////load methods - these place test data into the storage area
    ///// - used just once at startup


    @SuppressWarnings( "unchecked" )
    public HashMap<String, Book> readBooksMap() {

        //Returns a Map with name/value pairs being
        //   isbn -> Book
        return ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
    }


    @Override
    public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {

        return ( HashMap<String, CheckoutRecord> )readFromStorage( StorageType.CHECKOUT_RECORD );
    }


    @SuppressWarnings( "unchecked" )
    public HashMap<String, User> readUserMap() {

        //Returns a Map with name/value pairs being
        //   userId -> User
        return ( HashMap<String, User> )readFromStorage( StorageType.USERS );
    }


    @Override
    public HashMap<String, LibraryMember> readLibraryMemberMap() {

        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return ( HashMap<String, LibraryMember> )readFromStorage(
                StorageType.MEMBERS );
    }


    static Object readFromStorage( StorageType type ) {

        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath( OUTPUT_DIR, type.toString() );
            in = new ObjectInputStream( Files.newInputStream( path ) );
            retVal = in.readObject();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                }
                catch ( Exception e ) {
                }
            }
        }
        return retVal;
    }

    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair( S s, T t ) {

            first = s;
            second = t;
        }


        @Override
        public boolean equals( Object ob ) {

            if ( ob == null )
                return false;
            if ( this == ob )
                return true;
            if ( ob.getClass() != getClass() )
                return false;
            @SuppressWarnings( "unchecked" )
            Pair<S, T> p = ( Pair<S, T> )ob;
            return p.first.equals( first ) && p.second.equals( second );
        }


        @Override
        public int hashCode() {

            return first.hashCode() + 5 * second.hashCode();
        }


        @Override
        public String toString() {

            return "(" + first.toString() + ", " + second.toString() + ")";
        }

    }

    public Response getMemberCheckoutRecords( String memberId ) {

        Response response = new Response();

        List<CheckoutRecord> filteredCheckoutRecords = new ArrayList<>();

        @SuppressWarnings( "unchecked" )
        HashMap<String, CheckoutRecord> checkoutRecordsMap = ( HashMap<String,
                CheckoutRecord> )readFromStorage( StorageType.CHECKOUT_RECORD );

        List<CheckoutRecord> checkoutRecords = checkoutRecordsMap.values()
                .stream()
                .collect( Collectors.toList() );

        if ( checkoutRecords.size() > 0 ) {

            for ( CheckoutRecord checkoutRecord : checkoutRecords ) {

                if ( checkoutRecord.getLibraryMemberId().equals( memberId ) ) {
                    filteredCheckoutRecords.add( checkoutRecord );
                }
            }
            
            response.setData( filteredCheckoutRecords );
            response.setStatus( true );
            // getData( filteredCheckoutRecords );
        }
        else {
            response.setStatus( false );
        }

        return response;
    }


    public Response findMemberById( String memberId ) {

        Response response = new Response();

        @SuppressWarnings( "unchecked" )
        HashMap<String, LibraryMember> memberRecordsMap = ( HashMap<String,
                LibraryMember> )readFromStorage( StorageType.MEMBERS );

        if ( memberRecordsMap.size() > 0 ) {
            response.setData( memberRecordsMap.get( memberId ) );
            response.setStatus( true );
        }
        else {
            response.setMessage( "The member with id: " + memberId + " doesn't exist" );
            response.setStatus( false );
        }

        return response;
    }


    public static void main( String[] args ) {

        DataAccessFacade daf = new DataAccessFacade();
        //System.out.println( daf.getMemberRecords( "918" ) );

    }

}
