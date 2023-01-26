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


    }
}
