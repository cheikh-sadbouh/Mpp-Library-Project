package edu.miu.mpp.librarysystem.controller;

import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.service.UserService;

public class LibrarianController {
    private UserService userService = new UserService();

    public CheckoutRecord Checkout(String memberId, String Isbn) {

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
