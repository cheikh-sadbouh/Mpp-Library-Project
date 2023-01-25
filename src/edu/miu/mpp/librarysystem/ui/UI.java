package edu.miu.mpp.librarysystem.ui;

import java.util.Scanner;

import edu.miu.mpp.librarysystem.controller.Controller;
import edu.miu.mpp.librarysystem.controller.Response;
import edu.miu.mpp.librarysystem.dao.model.Author;
import edu.miu.mpp.librarysystem.dao.model.Book;
import edu.miu.mpp.librarysystem.dao.model.User;

enum UserInputType{
    STRING, INTEGER;
}
public class UI {
   private static Scanner scanner;
   private User user;
   private Controller controller;

   private Response response;

   public UI(){
       scanner = new Scanner(System.in);
       controller = new Controller();
   }
    public void start(){
       while (true){
           userLogin();
       }

    }

    public void userLogin(){
        String output = "Welcome to the Library System\nLogin to Continue";
        UI.displayConsole(output);

        UI.displayConsole("Enter ID: ");
        String userID = (String)UI.userInput(UserInputType.STRING);
        UI.displayConsole("Enter Password: ");
        String password = (String)UI.userInput(UserInputType.STRING);

        response = controller.authenticateUser(userID, password);
        if (response.getStatus()){
            /**
             * Next Stage
             */
            user = (User)response.getData();
        }else{
            UI.displayConsole(response.getMessage());
        }

    }

    public static void displayConsole(String message){
        System.out.println(message);
    }

    public static Object userInput(UserInputType inputType){

       switch (inputType){
           case INTEGER : return scanner.nextInt();

           default:  return scanner.nextLine();
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
        book.setAvailability( availability );

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
