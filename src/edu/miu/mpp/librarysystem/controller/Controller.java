package edu.miu.mpp.librarysystem.controller;

import edu.miu.mpp.librarysystem.dao.model.User;
import edu.miu.mpp.librarysystem.service.Service;

import java.util.Optional;

public class Controller {
    private Service service;
    public Controller(){
        service = new Service();
    }

    public Response authenticateUser(String Id, String password){
        Optional<User> user = service.authenticateUser(Id, password);
        if (user.isPresent()) return new Response("Successfully Logged In", true, user.get());
        return new Response("Error logging in", false, null);
    }
}
