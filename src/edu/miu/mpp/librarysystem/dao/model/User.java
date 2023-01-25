package edu.miu.mpp.librarysystem.dao.model;

import edu.miu.mpp.librarysystem.service.Auth;

import java.io.Serializable;
import java.util.Objects;

public class User extends Person implements Serializable {

    private static int uniqueID = 1;

    private String id;
    private String username;
    private String password;
    private Auth userRole;

    public User( String username, String password ) {

        this.username = username;
        this.password = password;
    }

    public User( String username, String password, Auth userRole ) {

        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.id = Integer.toString(User.uniqueID++);
    }

    public Auth getUserRole() {
        return userRole;
    }

    public void setUserRole(Auth userRole) {
        this.userRole = userRole;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && userRole == user.userRole;
    }


}
