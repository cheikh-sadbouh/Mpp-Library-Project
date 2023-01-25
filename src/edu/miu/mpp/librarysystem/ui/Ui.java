package edu.miu.mpp.librarysystem.ui;

import java.util.Objects;
import java.util.Scanner;

import edu.miu.mpp.librarysystem.controller.LibrarianController;
import edu.miu.mpp.librarysystem.dao.model.Author;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;

public class Ui {
    static  LibrarianController  librarianController = new LibrarianController();

    public  static  void checkOut(){


        Scanner scanner = new Scanner( System.in );
        String  userResponse ="";

        System.out.println("Current Screen :CheckOut");
        System.out.println("1. start Checkout\n"  + "0. Exit" );
        userResponse = scanner.next();

        if(userResponse.equalsIgnoreCase("0")){
            // call main screen

        }else if(userResponse.equalsIgnoreCase("1")){
            System.out.println("Enter MemberId");
            String memberId = scanner.next();
            System.out.println("Enter book ISBN ");
            String bookIsbn = scanner.next();
            CheckoutRecord record= librarianController.getCheckoutController(memberId,bookIsbn);
            if(Objects.nonNull(record)){
                System.out.println(record);
            }

        }else{
            checkOut();
        }

    }
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
        book.setTitle( title );
        book.setIsbn( isbn );

        //Add authors
        System.out.println( "Do you want to add authors to this book: (yes/no)" );
        String addAuthor = scanner.next();

        if ( addAuthor == "yes" ) {

            Author author = new Author();
        }

        System.out.println( book );
    }


    public static void main( String[] args ) {

        addNewBook();
    }
}
