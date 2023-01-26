package edu.miu.mpp.librarysystem.dao;

import edu.miu.mpp.librarysystem.dao.model.*;
import edu.miu.mpp.librarysystem.service.Auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestData {

    public static void loadData() {
        TestData td = new TestData();
        td.bookData();
        td.libraryMemberData();
        td.userData();
        DataAccess da = new DataAccessFacade();
        DataAccessFacade.loadCheckoutRecordMap();
    }

    public void bookData() {
        DataAccessFacade.loadBookMap(allBooks);
    }

    public void userData() {
        DataAccessFacade.loadUserMap(allUsers);
    }

    public void libraryMemberData() {
        LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
        members.add(libraryMember);
        libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
        members.add(libraryMember);

        DataAccessFacade.loadMemberMap(members);
    }

    List<LibraryMember> members = new ArrayList<LibraryMember>();

    @SuppressWarnings("serial")
    List<Address> addresses = new ArrayList<>() {
        {
            add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
            add(new Address("51 S. George", "Georgetown", "MI", "65434"));
            add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
            add(new Address("501 Central", "Mountain View", "CA", "94707"));
        }
    };

    @SuppressWarnings("serial")
    public List<Author> allAuthors = new ArrayList<>() {
        {
            add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
            add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
            add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
            add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
            add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
        }
    };

    @SuppressWarnings("serial")
    List<Book> allBooks = new ArrayList<>() {
        {
            add(new Book("23-11451", "The Big Fish", MaxBookCheckout.TWENTY_ONE_DAYS, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("28-12331", "Antartica", MaxBookCheckout.SEVEN_DAYS, Arrays.asList(allAuthors.get(2))));
            add(new Book("99-22223", "Thinking Java", MaxBookCheckout.TWENTY_ONE_DAYS, Arrays.asList(allAuthors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", MaxBookCheckout.SEVEN_DAYS, Arrays.asList(allAuthors.get(4))));
        }
    };

    @SuppressWarnings("serial")
    List<User> allUsers = new ArrayList<>() {
        {
            //ID: 1, password: xyz
            add(new User("101", "xyz", Auth.LIBRARIAN));
            
            //ID: 2, password: abc
            add(new User("102", "abc", Auth.ADMIN));
            
            //ID: 3, password: 111
            add(new User("103", "111", Auth.BOTH));
        }
    };
}
