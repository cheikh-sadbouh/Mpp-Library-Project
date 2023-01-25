package edu.miu.mpp.librarysystem.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import edu.miu.mpp.librarysystem.controller.LibrarianController;
import edu.miu.mpp.librarysystem.dao.model.Address;
import edu.miu.mpp.librarysystem.dao.model.Author;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.LibraryMember;

public class Ui {

    static LibrarianController librarianController = new LibrarianController();

    public static void checkOut() {

        Scanner scanner = new Scanner( System.in );
        String userResponse = "";

        System.out.println( "Current Screen :CheckOut" );
        System.out.println( "1. start Checkout\n" + "0. Exit" );
        userResponse = scanner.next();

        if ( userResponse.equalsIgnoreCase( "0" ) ) {
            // call main screen

        }
        else if ( userResponse.equalsIgnoreCase( "1" ) ) {
            System.out.println( "Enter MemberId" );
            String memberId = scanner.next();
            System.out.println( "Enter book ISBN " );
            String bookIsbn = scanner.next();
            CheckoutRecord record = librarianController.getCheckoutController( memberId, bookIsbn );
            if ( Objects.nonNull( record ) ) {
                System.out.println( record );
            }

        }
        else {
            checkOut();
        }

    }


    public void adminLoginUi() {

        System.out.println(
                "1. Add new book\n" + "2. Add library member\n"
                        + "3. Edit library member\n" + "0. Exit" );
    }


    public void addNewBook() {

        System.out.println( "-------Add new book-------" );
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Type book title:" );
        String title = scanner.next();
        System.out.println( "Type book isbn:" );
        String isbn = scanner.next();
        System.out.println( "Is the book available: (yes/no)" );
        String availability = scanner.next();

        Book book = new Book();
        book.setTitle( title );
        book.setAvailability( availability );

        //Add authors
        System.out.println( "Do you want to add authors to this book: (yes/no)" );
        String addAuthor = scanner.next();

        if ( addAuthor == "yes" ) {

            Author author = new Author();
        }

        System.out.println( book );
    }


    public LibraryMember addLibraryMember() {

        System.out.println( "-------Add libray member-------" );
        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type memberId:" );
        String memberId = scanner.nextLine();

        System.out.println( "Type member first name:" );
        String firstName = scanner.nextLine();

        System.out.println( "Type member last name:" );
        String lastName = scanner.nextLine();

        System.out.println( "Type member phone:" );
        String phone = scanner.nextLine();

        //scanner.close();

        Address address = createAddress();

        LibraryMember libraryMember = new LibraryMember( memberId,
                firstName, lastName, phone,
                address );

        System.out.println( libraryMember );

        return libraryMember;
    }
    
    private Author addAuthor( Book book ) {

        //Add book
        List<Book> books = new ArrayList<>();
        books.add( book );

        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type author first name:" );
        String firstName = scanner.nextLine();

        System.out.println( "Type author last name:" );
        String lastName = scanner.nextLine();

        System.out.println( "Type author biography:" );
        String bio = scanner.nextLine();

        System.out.println( "Type author phone:" );
        String phone = scanner.nextLine();

        //scanner.close();

        Address address = null;

        Author author = new Author( firstName, lastName, phone, address, bio, books );
        return author;
    }


    private Address createAddress() {

        System.out.println( "-------Add member address-------" );
        Scanner scanner = new Scanner( System.in );

        System.out.println( "Type street:" );
        String street = scanner.nextLine();

        System.out.println( "Type city:" );
        String city = scanner.nextLine();

        System.out.println( "Type state:" );
        String state = scanner.nextLine();

        System.out.println( "Type zip:" );
        String zip = scanner.nextLine();

        //scanner.close();

        Address address = new Address( street, city, state, zip );
        return address;
    }


    public static void main( String[] args ) {

        Ui ui = new Ui();
        ui.addLibraryMember();

    }