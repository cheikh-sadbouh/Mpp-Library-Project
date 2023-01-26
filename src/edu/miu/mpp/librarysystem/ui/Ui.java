package edu.miu.mpp.librarysystem.ui;

import java.util.*;

import edu.miu.mpp.librarysystem.controller.SystemController;
import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.model.*;
import edu.miu.mpp.librarysystem.service.Auth;
import edu.miu.mpp.librarysystem.service.UserService;

enum UserInputType {
    STRING, INTEGER;
}

enum DisplayMenu {
    Add_Book,
    Add_New_Library_Member,
    Add_a_Book_Copy,
    Check_Out_Book,
    Search_Member,
    Calculate_Late_Fee,
    Check_Book_Status,
    Find_Over_Due_Book
}

public class Ui {

    private static Scanner scanner;
    private User user;
    private Response response;
    private UserService userService;

    static SystemController systemController;

    public Ui() {

        systemController = new SystemController();
        scanner = new Scanner( System.in );
        userService = new UserService();
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

        response = systemController.authenticateUser( userID, password );
        if ( response.getStatus() ) {
            Ui.displayConsole( response.getMessage() + "\n" );
            user = ( User )response.getData();
            displayUserMenu();
        }
        else {
            Ui.displayConsole( response.getMessage() );
        }

    }


    public static void displayConsole( String message ) {

        System.out.println( message );
    }


    public static Object userInput( UserInputType inputType ) {

        //Redirect user to the main menu if input is zero

        switch ( inputType ) {
            case INTEGER:

                int inputInt = scanner.nextInt();
                return inputInt;

            default:
                String inputString = scanner.next();

                if ( inputString.equals( "0" ) ) {
                    //ui.displayUserMenu();
                }
                return inputString;
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
    public void displayUserMenu() {

        List<DisplayMenu> allList = new ArrayList<>();
        Collections.addAll( allList, DisplayMenu.Calculate_Late_Fee,
                DisplayMenu.Check_Book_Status );

        List<DisplayMenu> adminList = Arrays.asList( DisplayMenu.Add_Book,
                DisplayMenu.Add_New_Library_Member, DisplayMenu.Add_a_Book_Copy );
        List<DisplayMenu> libList = Arrays.asList( DisplayMenu.Check_Out_Book,
                DisplayMenu.Search_Member, DisplayMenu.Find_Over_Due_Book );

        if ( user.getUserRole() == Auth.BOTH ) {
            allList.addAll( adminList );
            allList.addAll( libList );
        }
        else if ( user.getUserRole() == Auth.ADMIN ) {
            allList.addAll( adminList );

        }
        else if ( user.getUserRole() == Auth.LIBRARIAN ) {
            allList.addAll( libList );
        }

        StringBuilder output = new StringBuilder(
                "Select from the Menu\nEnter the number to select\n" );
        for ( int i = 0; i < allList.size(); i++ ) {
            output.append( i + 1 ).append( ". " ).append( allList.get( i ) ).append( "\n" );
        }

        Ui.displayConsole( output.toString() );
        Integer menuSelection = ( Integer )Ui.userInput( UserInputType.INTEGER );

        Ui.displayConsole( "You selected: " + menuSelection + ". " + allList.get( menuSelection
                - 1 ) );
        menuSelection( allList.get( menuSelection - 1 ) );
    }


    public void menuSelection( DisplayMenu menuSelection ) {

        switch ( menuSelection ) {
            case Check_Out_Book:
                checkOut();
                break;
            case Add_Book:
                addNewBook();
                break;
            case Find_Over_Due_Book:
                findOverDueBookCopies();
                break;
            case Add_a_Book_Copy:
                addBookCopy();
            case Add_New_Library_Member:
                addLibraryMember();
                break;
            default:
                Ui.displayConsole( "You entered an invalid menu selection\n Try again" );
                displayUserMenu();

        }
    }


    public void checkOut() {

        Ui.displayConsole( "----------------------------------------------" );
        Ui.displayConsole( "| Current Screen :CheckOut | 0. Navigate Back |" );
        Ui.displayConsole( "----------------------------------------------" );

        Ui.displayConsole( "Enter MemberId" );
        scanner.nextLine();//to consume \n from displayConsole
        String memberId = ( String )Ui.userInput( UserInputType.STRING );
        if ( memberId.equalsIgnoreCase( "0" ) ) {
            // call main screen
            displayUserMenu();
        }
        else {
            Ui.displayConsole( "Enter book ISBN" );
            String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );
            Response recordResponse = systemController.Checkout( memberId, bookIsbn );
            if ( recordResponse.getStatus() ) {
                Ui.displayConsole( recordResponse.getData().toString() );
            }
            else {
                Ui.displayConsole( recordResponse.getMessage() );

            }
        }
        checkOut();

    }


    public void findOverDueBookCopies() {

        Ui.displayConsole( "Current Screen :Find Over Due Book\n" );
        Ui.displayConsole( "0. Navigate Back\n" );

        Ui.displayConsole( "Enter book ISBN\n" );
        String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );

        if ( bookIsbn.equalsIgnoreCase( "0" ) ) {
            // call main screen
            displayUserMenu();
        }
        else {
            Response recordResponse = systemController.getBookCopiesWithCheckoutRecord( bookIsbn );
            if ( recordResponse.getStatus() ) {
                Ui.displayConsole( recordResponse.getData().toString() );
            }
            else {
                Ui.displayConsole( recordResponse.getMessage() );
            }

        }

    }


    public void addBookCopy() {

        Ui.displayConsole( "Current Screen : Add a  Book Copy\n" );
        Ui.displayConsole( "0. Navigate Back\n" );

        Ui.displayConsole( "Enter book ISBN\n" );
        String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );
        if ( bookIsbn.equalsIgnoreCase( "0" ) ) {
            // call main screen
            displayUserMenu();
        }
        else {
            Response recordResponse = systemController.addNewBookCopy( bookIsbn, UUID.randomUUID()
                    .toString() );
            if ( recordResponse.getStatus() ) {
                Ui.displayConsole( recordResponse.getData().toString() );
            }
            else {
                Ui.displayConsole( recordResponse.getMessage() );
            }

        }
    }


    public void addNewBook() {

        System.out.println( "-------Add new book-------" );
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


    public void addLibraryMember() {

        System.out.println( "-------Add libray member-------" );

        System.out.println( "Type memberId:" );
        String memberId = ( String )Ui.userInput( UserInputType.STRING );

        //Check if memberId exists before adding more data
        if ( memberId.length() > 0 ) {
            while ( !userService.isMember( memberId ) ) {
                System.out.println( "MemberId: " + memberId
                        + " is already taken. Please select another Id. (0 - Menu)" );
                memberId = ( String )Ui.userInput( UserInputType.STRING );
            }
        }

        //How to exit from application
        if ( memberId.equals( "0" ) ) {
            displayUserMenu();
            return;
        }

        System.out.println( "Type member first name:" );
        String firstName = ( String )Ui.userInput( UserInputType.STRING );

        String lastName = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type member last name:" );

        String phone = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type member phone:" );

        Address address = createAddress();

        response = systemController.addLibraryMember( memberId, firstName, lastName, phone,
                address );

        if ( response.getStatus() ) {
            Ui.displayConsole( response.getMessage() + "\n" );
            //            user = ( LibraryMember )response.getData();
            displayUserMenu();
        }
        else {
            Ui.displayConsole( response.getMessage() );
        }
    }


    private Author addAuthor( Book book ) {

        //Add book
        List<Book> books = new ArrayList<>();
        books.add( book );

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

        String street = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "-------Add member address-------" );
        System.out.println( "Type street:" );

        String city = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type city:" );

        String state = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type state:" );

        String zip = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type zip:" );

        //scanner.close();

        Address address = new Address( street, city, state, zip );
        return address;
    }
}
