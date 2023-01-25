package edu.miu.mpp.librarysystem.ui;

import java.util.*;

import edu.miu.mpp.librarysystem.controller.LibrarianController;
import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.model.*;
import edu.miu.mpp.librarysystem.service.Auth;

enum UserInputType{
    STRING, INTEGER;
}

public class Ui {

    private static Scanner scanner;
    private User user;
    private Response response;

    static LibrarianController librarianController;

    public Ui(){
        librarianController = new LibrarianController();
        scanner = new Scanner(System.in);
    }


    public void start() {

        userLogin();
    }


    public void userLogin() {

        String output = "Welcome to the Library System\nLogin to Continue";
        Ui.displayConsole( output );

        Ui.displayConsole( "Enter ID: " );
        String userID = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "Enter Password: " );
        String password = ( String )Ui.userInput( UserInputType.STRING );

        response = librarianController.authenticateUser(userID, password);
        if (response.getStatus()){
            Ui.displayConsole(response.getMessage()+"\n");
            user = (User)response.getData();
            displayUserMenu();
        }else{
            Ui.displayConsole(response.getMessage());
        }

    }


    public static void displayConsole( String message ) {

        System.out.println( message );
    }


    public static Object userInput( UserInputType inputType ) {

        switch ( inputType ) {
            case INTEGER:
                return scanner.nextInt();

            default:
                return scanner.nextLine();
        }
    }


    public void librarianLoginUi() {

        System.out.println(
                "1. Checkout book\n" + "2. Search for library member\n"
                        + "0. Exit" );
    }


    public LibraryMember getLibraryMemberById( String memberId ) {

        return null;
    }

    /**
     * User List Menu
     */
    public void displayUserMenu(){
        List<String> allList = new ArrayList<>();
        Collections.addAll(allList, "Calculate Late Fee", "Check Book Status");

        List<String> adminList = Arrays.asList("Add Book", "Add New Library Member", "Add a Book Copy");
        List<String> libList = Arrays.asList("Check Out Book", "Search Member");

        if (user.getUserRole() == Auth.BOTH){
            allList.addAll(adminList);
            allList.addAll(libList);
        }else if (user.getUserRole() == Auth.ADMIN){
            allList.addAll(adminList);

        }else if (user.getUserRole() == Auth.LIBRARIAN) {
            allList.addAll(libList);
        }

        StringBuilder output = new StringBuilder("Select from the Menu\nEnter the number to select\n");
        for (int i = 0; i < allList.size(); i++) {
            output.append(i+1).append(". ").append(allList.get(i)).append("\n");
        }

        Ui.displayConsole(output.toString());
        Integer menuSelection = (Integer) Ui.userInput(UserInputType.INTEGER);

        Ui.displayConsole("You selected: "+menuSelection+". "+allList.get(menuSelection-1));
        menuSelection(allList.get(menuSelection-1));
    }

    public void menuSelection(String menuSelection){
        switch (menuSelection){
            case "Check Out Book":
                checkOut();
                break;
            case "Add Book":
                addNewBook();
                break;
            default:
                Ui.displayConsole("You entered an invalid menu selection\n Try again");
                displayUserMenu();

        }
    }



    public void checkOut() {

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
            Response recordResponse = librarianController.Checkout( memberId, bookIsbn );
            if ( recordResponse.getStatus()) {
                System.out.println( recordResponse.getData() );
            }else{
                System.out.println(recordResponse.getMessage());
            }

        }
        else {
            checkOut();
        }

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
        book.setIsbn( isbn );

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
}
