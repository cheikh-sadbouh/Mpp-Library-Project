package edu.miu.mpp.librarysystem.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.miu.mpp.librarysystem.dao.Address;
import edu.miu.mpp.librarysystem.dao.Author;
import edu.miu.mpp.librarysystem.dao.Book;
import edu.miu.mpp.librarysystem.dao.LibraryMember;

public class ui {

    public static void adminLoginUi() {

        System.out.println(
                "1. Add new book\n" + "2. Add library member\n"
                        + "3. Edit library member\n" + "0. Exit" );
    }


    public static void addNewBook() {

        System.out.println( "-------Add new book-------" );
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Type book title:" );
        String title = scanner.next();
        System.out.println( "Type book isbn:" );
        String isbn = scanner.next();
        System.out.println( "Is the book available: (yes/no)" );
        String availability = scanner.next();

        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        book.setTitle( title );
        book.setIsbn( isbn );
        book.setAvailability( availability );

        //Add authors
        System.out.println( "Do you want to add authors to this book: (yes/no)" );
        String addAuthor = scanner.next();

        if ( addAuthor.equals( "yes" ) ) {
            authors.add( addAuthor( book ) );
        }

        book.setAuthors( authors );

        System.out.println( book );
    }


    public static LibraryMember adLibraryMember() {

        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type memberId:" );
        String memberId = scanner.next();

        System.out.println( "Type author first name:" );
        String firstName = scanner.next();

        System.out.println( "Type author last name:" );
        String lastName = scanner.next();

        System.out.println( "Type author biography:" );
        String bio = scanner.next();

        System.out.println( "Type author phone:" );
        String phone = scanner.next();

        Address address = createAddress();

        LibraryMember libraryMember = new LibraryMember( memberId,
                firstName, lastName, phone,
                address );

        return libraryMember;
    }


    private static Author addAuthor( Book book ) {

        //Add book
        List<Book> books = new ArrayList<>();
        books.add( book );

        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type author first name:" );
        String firstName = scanner.next();

        System.out.println( "Type author last name:" );
        String lastName = scanner.next();

        System.out.println( "Type author biography:" );
        String bio = scanner.next();

        System.out.println( "Type author phone:" );
        String phone = scanner.next();

        Address address = null;

        Author author = new Author( firstName, lastName, phone, address, bio, books );
        return author;
    }


    private static Address createAddress() {

        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type street:" );
        String street = scanner.next();

        System.out.println( "Type city:" );
        String city = scanner.next();

        System.out.println( "Type state:" );
        String state = scanner.next();

        System.out.println( "Type zip:" );
        String zip = scanner.next();

        Address address = new Address( street, city, state, zip );
        return address;
    }


    public static void main( String[] args ) {

        addNewBook();
    }
}
