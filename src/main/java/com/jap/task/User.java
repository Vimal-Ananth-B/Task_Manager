package com.jap.task;

public class User {

    // Declare the attributes userName, password
    private String userName;
    private String password;

    public User()
    {
        //default constructor;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Declare getter, setter, and toString methods
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}