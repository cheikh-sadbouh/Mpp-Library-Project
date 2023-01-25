package edu.miu.mpp.librarysystem;

import edu.miu.mpp.librarysystem.dao.TestData;
import edu.miu.mpp.librarysystem.ui.Ui;

public class Main {

    public static void main(String[] args) {
        // Load data
        TestData.loadData();

        /**
         * Start Application
         */
        Ui app = new Ui();
        app.start();
    }
}
