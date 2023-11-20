package com.example.taskmanager.test;
import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.exceptions.UserNotFoundException;
import com.example.taskmanager.listas.Cola;
import com.example.taskmanager.model.mainClass.TaskManager;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.Rol;
import com.example.taskmanager.model.person.User;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void login() throws RegisterException {
        // Test normal case
        User user = new User("john_doe", "password123", Rol.COMMON,new Common("John Doe","1", "algo@gmail.com"));
        TaskManager taskManager = new TaskManager();
        taskManager.registerCommon(user);

        assertDoesNotThrow(() -> {
            Common loggedInUser = taskManager.login("john_doe", "password123");
            assertEquals("John Doe", loggedInUser.getName());
        });

        // Test invalid username
        assertThrows(UserNotFoundException.class, () -> taskManager.login("invalid_username", "password123"));

        // Test invalid password
        assertThrows(UserNotFoundException.class, () -> taskManager.login("john_doe", "invalid_password"));
    }

    @Test
    void registerCommon() {
        // Test normal case
        TaskManager taskManager = new TaskManager();
        User user = new User("john_doe", "password123", Rol.COMMON,new Common("John Doe","1", "algo@gmail.com"));
        assertDoesNotThrow(() -> taskManager.registerCommon(user));

        // Test when the user is already created
        assertThrows(RegisterException.class, () -> taskManager.registerCommon(user));
    }

    @Test
    void changeActivities() {
        // Test normal case
        TaskManager taskManager = new TaskManager();
        Activity activity1 = new Activity("A1", "Activity 1",true);
        Activity activity2 = new Activity("A2", "Activity 2",true);
        Task task1 = new Task("T1", "Task 1", true, LocalDateTime.now());
        Task task2 = new Task("T2", "Task 2", false,LocalDateTime.now());

        activity1.getTasksList().enqueue(task1);
        activity2.getTasksList().enqueue(task2);

        Cola<Task>auxListActivity1 = activity1.getTasksList();
        Cola<Task>auxListActivity2 = activity2.getTasksList();

        taskManager.changeActivities(activity1, activity2);

        assertFalse(activity1.getTasksList().isEmpty());
        assertFalse(activity2.getTasksList().isEmpty());
        assertSame(auxListActivity1, activity2.getTasksList());
        assertSame(auxListActivity2,activity1.getTasksList());
    }
}
