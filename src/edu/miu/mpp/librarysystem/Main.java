package edu.miu.mpp.librarysystem;

import edu.miu.mpp.librarysystem.dao.DataAccessFacade;
import edu.miu.mpp.librarysystem.dao.TestData;
import edu.miu.mpp.librarysystem.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {


    public static void main(String[] args) throws IOException {
        // Load data
       if(!Files.exists(Path.of(DataAccessFacade.OUTPUT_DIR))) {
           Files.createDirectory(Path.of(DataAccessFacade.OUTPUT_DIR));
           TestData.loadData();
       }

        /**
         * Start Application
         */
        Ui app = new Ui();
        app.start();

       // String s="CheckoutRecord{checkoutId='05b17e62-8af2-4c85-82d3-c774c055596a', libraryMember=LibraryMember{memberId='1001', record=null}, entries=[CheckoutRecordEntry{book=BookCopy{bookCopyId=2735955f-dd2a-413c-9853-fdde3d88043c, isAvailable=true}, checkoutDate=2023-01-25, dueDate=2023-02-15}]}";
//        Matcher m = Pattern.compile("bookCopyId=.*?(?=,\\s)").matcher(s);
//        while (m.find()) {
//            System.out.println( m.group(0));
//        }

//        List<String> properties= Arrays.asList("checkoutId","memberId","bookCopyId","checkoutDate","dueDate");
//        properties.forEach(property -> {
//            Matcher m = Pattern.compile(property+"=.*?(?=,|})").matcher(s);
//            while (m.find()) {
//                System.out.println( m.group(0).replace("'",""));
//
//            }
//        });
    }
}
