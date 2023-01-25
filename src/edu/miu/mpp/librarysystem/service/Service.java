package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.User;

import java.util.HashMap;
import java.util.Optional;

public class Service {

    private DataAccessFacade dataAccess;

    public Service() {
        dataAccess = new DataAccessFacade();
    }

    public Optional<User> authenticateUser(String Id, String password){
        HashMap<String, User> users = dataAccess.readUserMap();

        User user = users.get(Id);

        if (user == null) return Optional.empty();
        else if(user.getPassword().equals(password)) return Optional.of(user);
        else return Optional.empty();
    }
}
