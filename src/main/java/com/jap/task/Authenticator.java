package com.jap.task;

import java.util.*;

public class Authenticator {
    private List<User> userslist;
    private User authenticatedUser;

    public List<User> getUser() {
        return userslist;
    }

    public void setUser(List<User> userslist) {
        this.userslist = userslist;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    // Declare attributes: users - a list of users, authenticatedUser - the currently authenticated user
    // Generate getter and setter methods for both attributes

    public Authenticator() {
        userslist = new ArrayList<>();
        userslist.add(new User("John", "pass1"));
        userslist.add(new User("Vimal", "pass2"));
    }

    public Authenticator(List<User> userslist) {
        this.userslist = new ArrayList<>(userslist); // Create a copy to avoid modifying the original list
    }



    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username to authenticate.
     * @param password The password associated with the username.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        boolean flag=false;
        if (username == null || password == null) {
            flag=false;
        }
        for (User user : userslist) {
            if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                setAuthenticatedUser(user);
                flag=true;
            }
        }
        return flag;
    }


    /**
     * Authenticates a new user with the given username and password.
     *
     * @param username The username of the new user.
     * @param password The password associated with the new user.
     * @return The authenticated user if authentication is successful, null otherwise.
     */
    public User authenticateNewUser(String username, String password) {
        User resultantUser=new User();
        if (authenticateUser(username, password)) {
            System.out.println("Authentication successfully done");
            resultantUser=getAuthenticatedUser();
        } else {
            System.out.println("Authentication failed");
            resultantUser=null;
        }
        return resultantUser;
        // Call the authenticateUser method to check if the new user's credentials are valid
        // If the authenticateUser method returns true, set authenticatedUser to the authenticated user
        // Display an appropriate message
    }
}