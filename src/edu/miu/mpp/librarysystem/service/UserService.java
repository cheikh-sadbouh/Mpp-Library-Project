package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;
import edu.miu.mpp.librarysystem.dao.model.User;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


public class UserService implements Librarian{
    private DataAccessFacade dao;
    public UserService() {
        dao = new DataAccessFacade();
    }
    @Override
    public boolean isMember(String memberId) {
        return Objects.isNull(dao.getLibraryMember(memberId));
    }

    @Override
    public boolean isIsbnExist(String Isbn) {
        return Objects.isNull(dao.getBook(Isbn));
    }

    @Override
    public boolean isBookAvailable(String Isbn) {
        return Objects.isNull(dao.getBookCopy(Isbn));
    }

    @Override
    public CheckoutRecord createCheckoutRecord(String Isbn, String memberId) {
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(dao.getBookCopy(Isbn));
        CheckoutRecord checkoutRecord = new CheckoutRecord(dao.getLibraryMember(memberId));
        checkoutRecord.getEntries().add(checkoutRecordEntry);
        dao.addNewCheckoutRecord(checkoutRecord);
        return checkoutRecord;
    }

    public Optional<User> authenticateUser(String Id, String password){
        HashMap<String, User> users = dao.readUserMap();

        if (users == null) return Optional.empty();

        User user = users.get(Id);

        if (user == null) return Optional.empty();
        else if(user.getPassword().equals(password)) return Optional.of(user);
        else return Optional.empty();
    }


}
