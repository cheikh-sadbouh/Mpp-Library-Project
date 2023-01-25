package edu.miu.mpp.librarysystem.dao.model;

public class User extends Person {

    private String id;
    private String username;
    private String password;

    public User( String username, String password ) {

        this.username = username;
        this.password = password;
    }


    public String getId() {

        return id;
    }


    public String getUsername() {

        return username;
    }


    public String getPassword() {

        return password;
    }


    public void setId( String id ) {

        this.id = id;
    }


    public void setUsername( String username ) {

        this.username = username;
    }


    public void setPassword( String password ) {

        this.password = password;
    }

}
