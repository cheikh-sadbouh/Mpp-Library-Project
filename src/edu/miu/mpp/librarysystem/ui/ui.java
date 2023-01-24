package edu.miu.mpp.librarysystem.ui;

public class ui {

    public static void adminLoginUi() {

        System.out.println(
                "1. Add new book\n" + "2. Add library member\n"
                        + "3. Edit library member\n" + "0. Exit" );
    }


    public static void main( String[] args ) {

        adminLoginUi();
    }
}
