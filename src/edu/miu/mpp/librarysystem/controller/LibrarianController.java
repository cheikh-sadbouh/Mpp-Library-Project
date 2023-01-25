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
        return new Response("Error logging in", false, null);
    }

    public CheckoutRecord getCheckoutController(String memberId,String Isbn) {

        //check permission

        if(!userService.isMember(memberId)){
          System.out.println(memberId+" you are not yet a member ");
        } else if(!userService.isIsbnExist(Isbn)){
            System.out.println(Isbn+" there is no book match this ISBN");
        } else if(!userService.isBookAvailable(Isbn)){
            System.out.println(Isbn+" no available copy at the moment");
        }else{
            return userService.createCheckoutRecord(Isbn,memberId);

        }

        return  null;

    }
}
