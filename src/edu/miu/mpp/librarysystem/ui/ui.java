package edu.miu.mpp.librarysystem.ui;

import java.util.Scanner;

import edu.miu.mpp.librarysystem.dao.Book;

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
        book.setTitle( title );
        book.setIsbn( isbn );
        book.setAvailability( availability );

        System.out.println( book );
    }


    public static void main( String[] args ) {

        addNewBook();
    }
}
