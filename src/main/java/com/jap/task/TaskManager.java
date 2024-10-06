package com.jap.task;


import java.util.Scanner;

public class TaskManager {
    // Attributes for category and task operations
    private CategoryOperations categoryOperations;
    private TaskOperations taskOperations;

    /**
     * Constructs a new TaskManager object.
     * Initializes category and task operation objects.
     */
    public TaskManager() {
        // Initialize category and task operation objects
        categoryOperations = new CategoryOperations();
        taskOperations = new TaskOperations();
    }

    /**
     * Allows the authenticated user to interact with the task manager by providing menu choices.
     *
     * @param authenticatedUser The authenticated user accessing the task manager.
     */
    public void takeChoices(User authenticatedUser) throws TaskNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String categoryName;
        Category selectedCategory;
        int choice;

        // Display the menu options and handle user choices until the user chooses to exit
        do {
            System.out.println("\nTask Manager Menu");
            System.out.println("1. Add a Task");
            System.out.println("2. List all Tasks");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Remove a Task");
            System.out.println("5. Add a Category");
            System.out.println("6. List all Categories");
            System.out.println("7. Search for a Task by Name");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the category name:");
                    categoryName= scanner.nextLine();
                    selectedCategory=categoryOperations.findCategory(categoryName);
                    if(selectedCategory!=null)
                    {
                        System.out.println("Enter the task Name:");
                        String task=scanner.nextLine();
                        System.out.println("Enter the priority:");
                        int priority=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the description:");
                        String description=scanner.nextLine();
                        Task newTask = new Task(task, priority, description, false, selectedCategory);
                        taskOperations.addTask(selectedCategory, newTask, authenticatedUser);
                        System.out.println("Task added Successfully");
                    }
                    else {
                        System.out.println( "Category not found,Add the Category first");
                    }
                    // Add a new task
                    // Enter category name and fetch the category
                    // Check if category is present
                    // If present, display appropriate message to add task
                    // If category is not present, display appropriate message
                    break;
                case 2:
                    // List all tasks
                    System.out.print("Enter Category Name: ");
                    categoryName = scanner.next();
                    for (String task : taskOperations.listAllTasks(categoryName)) {
                        System.out.println(task);
                    }
                    break;
                case 3:
                    System.out.println(("Enter task name:"));
                    String taskFind=scanner.nextLine();
                    System.out.println(("Enter updated Status(Completed/pending):"));
                    String updatedStatus=scanner.nextLine();
                    if(taskOperations.markTaskAsCompleted(taskFind,updatedStatus))
                    {
                        System.out.println("Status updated Successfully");
                    }
                    else
                    {
                        System.out.println("Invalid Status (or) Status updation failed");
                    }
                    // Mark a task as completed
                    // Get the task name and task status
                    break;
                case 4:
                    System.out.print("Enter Task Name: ");
                    taskFind = scanner.nextLine();
                    try {
                        if (taskOperations.removeTask(taskFind)) {
                            System.out.println("Task removed successfully.");
                        }
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // Remove a task
                // Enter task name and handle exceptions
                case 5:
                    System.out.print("Enter Category Name: ");
                    categoryName = scanner.nextLine();
                    if (categoryOperations.addCategory(categoryName)) {
                        System.out.println("Category added successfully.");
                    } else {
                        System.out.println("Category already exists.");
                    }
                    // Add a new category
                    // Enter category name
                    break;
                case 6:
                    for (Category category : categoryOperations.listAllCategories()) {
                        System.out.println(category.getCategoryName());
                    }
                    // List all categories
                    break;
                case 7:
                    System.out.print("Enter Task Name: ");
                    taskFind = scanner.nextLine();
                    Task task = taskOperations.searchTasksByName(taskFind);
                    if (task != null) {
                        System.out.println(task);
                    } else {
                        System.out.println("Task not found.");
                    }
                    // Search for a task by name
                    // Enter task name to search
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting Task Manager...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        } while (choice != 8);

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}