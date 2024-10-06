package com.jap.task;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws TaskNotFoundException {
        Authenticator userAuthenticator = new Authenticator();
        TaskManager taskManagerNew = new TaskManager();
        User authenticatedUser = null;
        while (authenticatedUser == null) {
            System.out.println("************************Your's Task Manager************************");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Username :");
            String username = scanner.next();
            System.out.print("Enter your Password :");
            String password = scanner.next();
            authenticatedUser = userAuthenticator.authenticateNewUser(username, password);
        }
        taskManagerNew.takeChoices(authenticatedUser);
    }
}