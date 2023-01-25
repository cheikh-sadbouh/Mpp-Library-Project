package edu.miu.mpp.librarysystem.service;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecord;
import edu.miu.mpp.librarysystem.dao.model.CheckoutRecordEntry;

import java.util.Objects;

public class UserService implements Librarian {

    private DataAccessFacade dao = new DataAccessFacade();

    @Override
    public boolean isMember( String memberId ) {

        return Objects.isNull( dao.getLibraryMember( memberId ) );
    }


    @Override
    public boolean isIsbnExist( String Isbn ) {

        return Objects.isNull( dao.getBook( Isbn ) );
    }


    @Override
    public boolean isBookAvailable( String Isbn ) {

        return Objects.isNull( dao.getBookCopy( Isbn ) );
    }


    @Override
    public CheckoutRecord createCheckoutRecord( String Isbn, String memberId ) {

        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry( dao.getBookCopy(
                Isbn ) );
        CheckoutRecord checkoutRecord = new CheckoutRecord( dao.getLibraryMember( memberId ) );
        checkoutRecord.getEntries().add( checkoutRecordEntry );
        dao.addNewCheckoutRecord( checkoutRecord );
        return checkoutRecord;
    }

}
