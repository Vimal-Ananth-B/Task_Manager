package com.jap.task;

import java.util.*;

public class TaskOperations {
    // Declare the attributes taskNames, userTaskMap, categoryTaskMap
    private Set<String> taskNames;
    private Map<User, List<Task>> userTaskMap;
    private Map<Category, List<Task>> categoryTaskMap;

    public TaskOperations() {
        // Initialize the attributes
        taskNames = new HashSet<>();
        userTaskMap = new HashMap<>();
        categoryTaskMap = new HashMap<>();
    }

    public boolean addTask(Category selectedCategory, Task task, User authenticatedUser) {
        boolean flag = false;
        // Check if task is already present in taskNames
        if (taskNames.contains(task.getTaskName())) {
            // If present, display appropriate message
            System.out.println("Task already exists.");
        } else {
            // If not present, get the userTask from the authenticatedUser
            List<Task> userTasks = userTaskMap.get(authenticatedUser);
            // Check if userTasks is not equal to null
            if (userTasks == null) {
                userTasks = new ArrayList<>();
                userTaskMap.put(authenticatedUser, userTasks);
            }
            // Add the task to the list
            userTasks.add(task);
            // Check if categoryTasks is not equal to null
            List<Task> categoryTasks = categoryTaskMap.get(selectedCategory);
            if (categoryTasks == null) {
                categoryTasks = new ArrayList<>();
                categoryTaskMap.put(selectedCategory, categoryTasks);
            }
            // Add the task to the list
            categoryTasks.add(task);
            // Add the task to the taskNames
            taskNames.add(task.getTaskName());
            flag = true;
        }
        return flag;
    }

    public List<String> listAllTasks(String categoryName) {
        List<String> sortedTasks = new ArrayList<>();
        // Fetch categories by categoryName using getCategoryByName method of the category class
        Category selectedCategory = CategoryOperations.getCategoryByName(categoryTaskMap, categoryName);
        // If the selectedCategory is null, display appropriate message
        if (selectedCategory == null) {
            System.out.println("Category not found.");
        } else {
            // If selectedCategory is not null
            List<Task> tasks = categoryTaskMap.get(selectedCategory);
            if (tasks != null) {
                for (Task task : tasks) {
                    // Generate a string for each task
                    String taskDetails = "Category: " + categoryName + " - " +
                            "Name: " + task.getTaskName() + " Priority: " + task.getPriority() +
                            " Description: " + task.getDescription() + " Status: " + (task.isCompleted() ? "Completed" : "Pending");
                    // Add the string to the list
                    sortedTasks.add(taskDetails);
                }
            }
        }
        // Return the list
        return sortedTasks;
    }

    public boolean markTaskAsCompleted(String taskToComplete, String statusInput) {
        boolean result = false;
        // Iterate the list of task from categoryTaskMap
        for (List<Task> tasks : categoryTaskMap.values()) {
            for (Task task : tasks) {
                // Check if the task with the given name is present
                if (task.getTaskName().equals(taskToComplete)) {
                    // If the given statusInput is completed, setCompleted to true
                    if ("completed".equalsIgnoreCase(statusInput)) {
                        task.setCompleted(true);
                        result = true;
                    } else if ("pending".equalsIgnoreCase(statusInput)) {
                        // If the given statusInput is pending, setCompleted to false
                        task.setCompleted(false);
                        result = true;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public boolean removeTask(String taskToRemove) throws TaskNotFoundException {
        boolean found = false;
        // Iterate the list of task from categoryTaskMap
        for (List<Task> tasks : categoryTaskMap.values()) {
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                // Check if the task with the given name is present
                if (task.getTaskName().equals(taskToRemove)) {
                    // Remove the task
                    iterator.remove();
                    found = true;
                    break;
                }
            }
        }
        // Remove the task from taskNames
        if (found) {
            taskNames.remove(taskToRemove);
        } else {
            // If task is not removed, throw TaskNotFoundException
            throw new TaskNotFoundException("Task not found.");
        }
        return found;
    }

    public Task searchTasksByName(String taskName) {
        Task foundTask = null;
        // Iterate the list of task from categoryTaskMap
        for (List<Task> tasks : categoryTaskMap.values()) {
            for (Task task : tasks) {
                // Check if the task with the given name is present
                if (task.getTaskName().equals(taskName)) {
                    foundTask = task;
                    break;
                }
            }
            if (foundTask != null) {
                break;
            }
        }
        // Return null if task is not found
        return foundTask;
    }
}