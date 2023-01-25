package edu.miu.mpp.librarysystem.controller;

import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.service.UserService;

import java.util.Objects;
import java.util.Optional;

public class SystemController {
    private UserService userService;

    public SystemController(){
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

    public Response getBookCopiesWithCheckoutRecord(String isbn){
        //check permission
        Response response =  new Response();
       String serviceResponse = userService.getBookCopiesWithCheckoutRecord(isbn);
           if(Objects.isNull(serviceResponse)) response.setMessage("book was not found");
           else {
               response.setData(serviceResponse);
               response.setStatus(true);
           }
        return  response;
    }

    public  Response  addNewBookCopy(String isbn, String bookCopyId){
        //check permission
        Response response =  new Response();

        if(userService.addNewBookCopy(isbn, bookCopyId)){
            response.setData("mew Book Copy has been added !");
            response.setStatus(true);
        }else {
            response.setMessage("internal server error , bookCopy has not been added ");
        }
        return  response;

    }
}
