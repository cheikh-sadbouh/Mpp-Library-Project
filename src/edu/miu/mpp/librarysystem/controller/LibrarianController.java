package edu.miu.mpp.librarysystem.controller;

import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.service.UserService;

import java.util.Optional;

public class LibrarianController {
    private UserService userService;

    public LibrarianController(){
        userService = new UserService();
    }

    public Response authenticateUser(String Id, String password){
        Optional<User> user = userService.authenticateUser(Id, password);
        if (user.isPresent()) return new Response("Successfully Logged In", true, user.get());
        return new Response("Invalid Credentials", false, null);
    }

    public Response Checkout(String memberId, String Isbn) {

        //check permission

          Response response =  new Response();

        if(!userService.isMember(memberId)){
            response.setMessage(memberId+" you are not yet a member \n");
        } else if(!userService.isIsbnExist(Isbn)){
            response.setMessage(response.getMessage() + Isbn+" there is no book match this ISBN \n");
        } else if(!userService.isBookAvailable(Isbn)){
            response.setMessage(response.getMessage() + Isbn+" no available copy at the moment \n");
        }else{
             response.setData(userService.createCheckoutRecord(Isbn,memberId).toString());
             response.setStatus(true);
        }


        return response;

    }
    public String getBookCopiesWithCheckoutRecord(String isbn){
        return userService.getBookCopiesWithCheckoutRecord(isbn);
    }
}
