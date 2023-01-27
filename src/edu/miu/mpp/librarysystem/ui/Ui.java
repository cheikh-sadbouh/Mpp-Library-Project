package edu.miu.mpp.librarysystem.ui;

import java.util.*;

import edu.miu.mpp.librarysystem.Main;
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
        scanner = new Scanner( System.in ).useDelimiter( "\\n" );
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
            Ui.displayConsole( "Welcome " + user.getUsername() );
            displayUserMenu();
        }
        else {
            Ui.displayConsole( response.getMessage() );
            userLogin();
        }
    }


    public static void displayConsole( String message ) {

        System.out.println( message );
    }


    public static void displayScreenHeader( String currentScreen ) {

        Ui.displayConsole(
                "+-------------------------------------------------------------------------------------------------------------+" );
        Ui.displayConsole( "|  Current Screen : " + currentScreen
                + "  | 0.Back to Main Screen                                                  " );
        Ui.displayConsole(
                "+-------------------------------------------------------------------------------------------------------------+" );
    }


    public void displayLogout() {

        Ui.displayConsole(
                "+-------------------------------------------------------------------------------------------------------------+" );
        Ui.displayConsole(
                "Select Option to Continue\n1. Logout and Exit Program \n2. Continue to Menu" );
        Ui.displayConsole(
                "+-------------------------------------------------------------------------------------------------------------+" );

        if ( ( Integer )Ui.userInput( UserInputType.INTEGER ) == 1 ) {
            Ui.displayConsole( "Logging Out from System." );
            System.exit( 1 );
        }
        displayUserMenu();
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
                    /** to be reviewed */
                    Main.app.displayUserMenu();
                }
                return inputString;
        }
    }


    public LibraryMember getLibraryMemberById( String memberId ) {

        return null;
    }


    /**
     * User List Menu
     */
    public void displayUserMenu() {

        List<DisplayMenu> allList = new ArrayList<>();
        Collections.addAll( allList, DisplayMenu.Calculate_Late_Fee );

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
                addBook();
                break;
            case Find_Over_Due_Book:
                findOverDueBookCopies();
                break;
            case Add_a_Book_Copy:
                addBookCopy();
            case Add_New_Library_Member:
                addLibraryMember();
                break;
            case Search_Member:
                searchMember();
                break;
            case Calculate_Late_Fee:
                calculateBookLateFee();
                break;
            default:
                Ui.displayConsole( "You entered an invalid menu selection\n Try again" );
                displayLogout();
        }
    }


    public void checkOut() {

        displayScreenHeader( DisplayMenu.Check_Out_Book.toString() );

        Ui.displayConsole( "Enter MemberId" );

        String memberId = ( String )Ui.userInput( UserInputType.STRING );

        Ui.displayConsole( "Enter book ISBN" );
        String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );
        Response recordResponse = systemController.Checkout( memberId, bookIsbn );
        if ( recordResponse.getStatus() ) {
            Ui.displayConsole( recordResponse.getData().toString() );

        }
        else {
            Ui.displayConsole( recordResponse.getMessage() );

        }

        displayLogout();
    }


    public void findOverDueBookCopies() {

        displayScreenHeader( DisplayMenu.Find_Over_Due_Book.toString() );

        Ui.displayConsole( "Enter book ISBN" );
        String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );

        Response recordResponse = systemController.getBookCopiesWithCheckoutRecord( bookIsbn );
        if ( recordResponse.getStatus() ) {

            StringBuilder sb = new StringBuilder( recordResponse.getData().toString() );
            int newLineCount = 0;
            int index = sb.indexOf( "\n" );
            while ( index != -1 ) {
                newLineCount++;
                if ( newLineCount == 3 ) {
                    sb.insert( index + 1,
                            "---------------------------------------------------------\n" );
                    newLineCount = -1;
                }
                index = sb.indexOf( "\n", index + 1 );
            }
            sb.insert( 0, "---------------------Book CheckOut Record ---------------" + "\n" );

            Ui.displayConsole( sb.toString() );
        }
        else {
            Ui.displayConsole( recordResponse.getMessage() );
        }

        displayLogout();
    }


    public void addBookCopy() {

        displayScreenHeader( DisplayMenu.Add_a_Book_Copy.toString() );

        Ui.displayConsole( "Enter book ISBN" );
        String bookIsbn = ( String )Ui.userInput( UserInputType.STRING );
        displayScreenHeader( DisplayMenu.Add_a_Book_Copy.toString() );

        String bookCopyId = UUID.randomUUID().toString();
        Ui.displayConsole( "a new bookCopy Id has been generated " );
        Ui.displayConsole( "bookCopyId = " + bookCopyId );
        Ui.displayConsole( "---------------------------------------------------------" );
        Response recordResponse = systemController.addNewBookCopy( bookIsbn, bookCopyId );
        if ( recordResponse.getStatus() ) {
            Ui.displayConsole( recordResponse.getData().toString() );
            Ui.displayConsole( "---------------------------------------------------------" );

        }
        else {
            Ui.displayConsole( recordResponse.getMessage() );
        }

        displayLogout();
    }


    public void addBook() {

        displayScreenHeader( DisplayMenu.Add_a_Book_Copy.toString() );

        String title, isbn;
        MaxBookCheckout maxBookCheckout;
        Integer numberOfCopies, authorCount;
        List<Author> authors = new ArrayList<>();

        Ui.displayConsole( "Enter details to add a new Book" );
        Ui.displayConsole( "" );

        Ui.displayConsole( "ISBN: " );
        isbn = ( String )Ui.userInput( UserInputType.STRING );

        Ui.displayConsole( "Title: " );
        title = ( String )Ui.userInput( UserInputType.STRING );

        Ui.displayConsole( "Enter Number of Authors " );
        authorCount = ( Integer )Ui.userInput( UserInputType.INTEGER );
        for ( int i = 1; i <= authorCount; i++ ) {
            Ui.displayConsole( i + "." );
            Ui.displayConsole( "Enter first name of Author: " );
            String firstName = ( String )Ui.userInput( UserInputType.STRING );

            Ui.displayConsole( "Enter last name of Author: " );
            String lastName = ( String )Ui.userInput( UserInputType.STRING );

            Ui.displayConsole( "Enter phone of Author: " );
            String phone = ( String )Ui.userInput( UserInputType.STRING );

            authors.add( new Author( firstName, lastName, phone, null, null ) );
            Ui.displayConsole( "-----------------------------------------------" );
        }

        Ui.displayConsole( "Number of Copies: " );
        numberOfCopies = ( Integer )Ui.userInput( UserInputType.INTEGER );

        Ui.displayConsole( "Select max checkout length" );
        List<MaxBookCheckout> maxBookCheckoutList = Arrays.asList( MaxBookCheckout.SEVEN_DAYS,
                MaxBookCheckout.TWENTY_ONE_DAYS );
        StringBuilder maxOutput = new StringBuilder();
        for ( int i = 0; i < maxBookCheckoutList.size(); i++ ) {
            maxOutput.append( i + 1 ).append( ". " ).append( maxBookCheckoutList.get( i )
                    .getDays() ).append( "\n" );
        }
        Ui.displayConsole( String.valueOf( maxOutput ) );
        Integer maxMenuSelection = ( Integer )Ui.userInput( UserInputType.INTEGER );
        Ui.displayConsole( "You selected " + maxBookCheckoutList.get( maxMenuSelection - 1 )
                .getDays() );
        maxBookCheckout = maxBookCheckoutList.get( maxMenuSelection - 1 );

        response = systemController.addBook( isbn, title, maxBookCheckout, authors,
                numberOfCopies );
        Ui.displayConsole( response.getMessage() );

        displayLogout();

    }


    public void calculateBookLateFee() {

        Ui.displayConsole( "Enter ISBN for book: " );
        String isbn = ( String )Ui.userInput( UserInputType.STRING );

        Ui.displayConsole( "Enter Member Id: " );
        String memberId = ( String )Ui.userInput( UserInputType.STRING );

        response = systemController.getCheckoutOverDueFee( memberId, isbn );

        if ( response.getStatus() ) {
            Ui.displayConsole( "Fee is $ " + String.format( "%.2f", response.getData() ) );
        }
        else {
            Ui.displayConsole( response.getMessage() );
        }

        displayLogout();
    }


    public void addLibraryMember() {

        Ui.displayConsole( "-------Add libray member-------" );

        Ui.displayConsole( "Type memberId:" );
        String memberId = ( String )Ui.userInput( UserInputType.STRING );

        //Check if memberId exists before adding more data
        if ( memberId.length() > 0 ) {
            while ( !userService.isMember( memberId ) ) {
                Ui.displayConsole( "MemberId: " + memberId
                        + " is already taken. Please select another Id. (0 - Menu)" );
                memberId = ( String )Ui.userInput( UserInputType.STRING );
            }
        }

        Ui.displayConsole( "Type member first name:" );
        String firstName = ( String )Ui.userInput( UserInputType.STRING );

        String lastName = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "Type member last name:" );

        String phone = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "Type member phone:" );

        Address address = createAddress();

        response = systemController.addLibraryMember( memberId, firstName, lastName, phone,
                address );

        if ( response.getStatus() ) {
            Ui.displayConsole( response.getMessage() + "\n" );
        }
        else {
            Ui.displayConsole( response.getMessage() );
        }

        displayLogout();
    }


    public void searchMember() {

        Ui.displayConsole( "-------Add member id-------" );

        Ui.displayConsole( "Type memberId:" );
        String memberId = ( String )Ui.userInput( UserInputType.STRING );

        //Check if memberId exists before adding more data
        if ( memberId.length() > 0 ) {
            while ( userService.isMember( memberId ) ) {
                Ui.displayConsole( "MemberId: " + memberId
                        + " doesn't exist. Try another memberId (0 - Menu)" );
                memberId = ( String )Ui.userInput( UserInputType.STRING );
            }
        }
        Ui.displayConsole( "Do you want to show the member's checkout records? (yes/no)" );
        String showCheckoutRecords = ( String )Ui.userInput( UserInputType.STRING );

        if ( showCheckoutRecords.equals( "yes" ) ) {
            response = systemController.getCheckoutRecordsMemberById( memberId );

            if ( response.getData() == null ) {
                Ui.displayConsole( "The member has no checkout records" );
                displayUserMenu();
                return;
            }

            List<CheckoutRecord> checkoutRecords = ( List<CheckoutRecord> )response.getData();
            printCheckoutRecordEntries( checkoutRecords );
        }

        displayLogout();
    }


    private void printCheckoutRecordEntries( List<CheckoutRecord> checkoutRecords ) {

        for ( CheckoutRecord checkoutRecord : checkoutRecords ) {
            System.out.println( "Member Id: " + checkoutRecord.getLibraryMemberId() );
            System.out.println( "Checkout Id: " + checkoutRecord.getCheckoutId() );
            System.out.println( "***************************************************" );
            List<CheckoutRecordEntry> entries = checkoutRecord.getEntries();

            for ( CheckoutRecordEntry entry : entries ) {
                System.out.printf( "%-50s%-22s%-22s%-22s\n", "Book Copy Id", "Available",
                        "Checkout date", "Due Date" );

                System.out.printf( "%-50s%-22s%-22s%-22s\n",
                        entry.getBookCopy().getBookCopyId(),
                        entry.getBookCopy().isAvailable(),
                        entry.getCheckoutDate(),
                        entry.getDueDate() );
            }
            System.out.println( "\n" );
            System.out.println(
                    "******************************************************************************************************" );
        }

    }


    private Address createAddress() {

        String street = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "-------Add member address-------" );
        Ui.displayConsole( "Type street:" );

        String city = ( String )Ui.userInput( UserInputType.STRING );
        System.out.println( "Type city:" );

        String state = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "Type state:" );

        String zip = ( String )Ui.userInput( UserInputType.STRING );
        Ui.displayConsole( "Type zip:" );

        //scanner.close();

        Address address = new Address( street, city, state, zip );
        return address;
    }

}
