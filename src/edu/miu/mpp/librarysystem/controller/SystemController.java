/*
    =======================================================================================
    This code is part of mpp project.

    ========================================================================================
    Authors :Cheikh Sad Bouh Ahmed Brahim, Emmanuel Coffie Debrah, Kasaija Ronald, 
    ========================================================================================
*/
package edu.miu.mpp.librarysystem.controller;

import edu.miu.mpp.librarysystem.dao.model.Address;
import edu.miu.mpp.librarysystem.dao.model.Author;
import edu.miu.mpp.librarysystem.dao.model.MaxBookCheckout;
import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.service.Auth;
import edu.miu.mpp.librarysystem.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SystemController {

    private UserService userService;
     private   Optional<User> user;
    public SystemController() {

        userService = new UserService();
    }


    public Response authenticateUser( String Id, String password ) {

         user = userService.authenticateUser( Id, password );
        if ( user.isPresent() )
            return new Response( "Successfully Logged In", true, user.get() );
        return new Response( "Invalid Credentials", false, null );
    }

     public  Response isAuthorized(List<Auth> list) {

         Response response = new Response();
         boolean hasAnyPermission =false;
         for(Auth permission : list){
             if (user.get().getUserRole().equals(permission)){
                 hasAnyPermission=true;
             }
         }
         if(hasAnyPermission)
             response.setStatus(true);
         else
             response.setMessage( user.get().getUsername()+" You Are Not Authorized to Access This Resource" );

         return response;
     }
    public Response Checkout( String memberId, String Isbn ) {
        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.LIBRARIAN,Auth.BOTH));

       if(!response.getStatus())
              return  response;
        else response = new Response();



        if ( userService.isMember( memberId ) ) {
            response.setMessage( memberId + " is  not yet a member \n" );
        }
        else if ( userService.isIsbnExist( Isbn ) ) {
            response.setMessage( Isbn + " there is no book match this ISBN \n" );
        }
        else if ( userService.isBookAvailable( Isbn ) ) {
            response.setMessage( " no available copy at the moment for  Isbn " + Isbn + "\n" );
        }
        else {

            response.setData( "new Record :\n" + userService.createCheckoutRecord( Isbn,
                    memberId ) );
            response.setStatus( true );
        }

        return response;

    }

    public Response getCheckoutOverDueFee(String memberId, String Isbn){

        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.ADMIN,Auth.LIBRARIAN,Auth.BOTH));

        if(!response.getStatus())
            return  response;
        else response = new Response();

        if ( userService.isMember( memberId ) ) {
            response.setMessage( memberId + " is  not yet a member \n" );
        } else if ( userService.isIsbnExist( Isbn ) ) {
            response.setMessage( Isbn + " there is no book match this ISBN \n" );
        } else if ( userService.isBookAvailable( Isbn ) ) {
            response.setMessage( " no available copy at the moment for  Isbn " + Isbn + "\n" );
        }else{
            response.setStatus(true);
            response.setData(userService.calculateOverDueBook(Isbn, memberId));
        }

        return response;
    }


    public Response getBookCopiesWithCheckoutRecord( String isbn ) {

        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.LIBRARIAN,Auth.BOTH));

        if(!response.getStatus())
            return  response;
        else response = new Response();

        String serviceResponse = userService.getBookCopiesWithCheckoutRecord( isbn );
        if ( Objects.isNull( serviceResponse ) )
            response.setMessage( "book was not found" );
        else {
            response.setData( serviceResponse );
            response.setStatus( true );
        }
        return response;
    }


    public Response addNewBookCopy( String isbn, String bookCopyId ) {

        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.ADMIN,Auth.BOTH));

        if(!response.getStatus())
            return  response;
        else response = new Response();

        if ( userService.addNewBookCopy( isbn, bookCopyId ) ) {
            response.setData( "mew Book Copy has been added ! with copy Number = "
                    + bookCopyId );
            response.setStatus( true );
        }
        else {
            response.setMessage( "internal server error , bookCopy has not been added " );
        }
        return response;

    }


    public Response addLibraryMember( String memberId, String firstName, String lastName,
            String phone, Address address ) {
        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.ADMIN,Auth.BOTH));

        if(!response.getStatus())
            return  response;

         response = userService.addLibraryMember( memberId, firstName, lastName, phone, address );
        return response;
    }


    public Response getCheckoutRecordsMemberById( String memberId ) {

        Response response = userService.getMemberCheckoutRecords( memberId );
        return response;
    }


    public Response findMemberById( String memberId ) {

        Response response = userService.findMemberById( memberId );
        return response;
    }


    public Response addBook( String isbn, String title, MaxBookCheckout maxBookCheckout,
            List<Author> authors, Integer numberOfCopies ) {

        //check permission
        Response  response = isAuthorized(Arrays.asList(Auth.ADMIN,Auth.BOTH));

        if(!response.getStatus())
            return  response;

        if ( userService.addBook( isbn, title, maxBookCheckout, authors, numberOfCopies ) ) {
            return new Response( "Successfully added new Book", true, null );
        }
        else {
            return new Response( "Error in adding new Book", false, null );
        }
    }
}
