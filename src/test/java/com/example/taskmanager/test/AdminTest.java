package com.example.taskmanager.test;

import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void assignProcess() {
        Admin admin = new Admin("admin","a");
        // Test normal case
        Common employee = new Common ("John","1","asdahbda@gmail.com");
        MyProcess process = new MyProcess("P1", "Process 1");
        assertDoesNotThrow(() -> admin.assignProcess(employee, process));

        // Test when the process is already assigned
        assertThrows(ProcessException.class, () -> admin.assignProcess(employee, process));
    }

    @Test
    void addEmployee() {
        Admin admin = new Admin("admin","a");
        // Test normal case
        Common common = new Common("John","1","hhsbahjda@gmail.com");
        assertDoesNotThrow(() -> admin.addEmployee(common));

        // Test when the employee is already added
        assertThrows(RegisterException.class, () -> admin.addEmployee(common));
    }

    @Test
    void createProcess() {
        Admin admin = new Admin("admin","a");
        // Test normal case
        assertDoesNotThrow(() -> admin.createProcess("P1", "Process 1"));

        // Test when the process is already created
        assertThrows(ProcessException.class, () -> admin.createProcess("P1", "Process 1"));
    }

    @Test
    void createActivityGivingName() {
        Admin admin = new Admin("admin","a");

        // Test normal case
        MyProcess process = new MyProcess("P1", "Process 1");
        Activity activity = new Activity("A1", "Activity 1",true);
        Activity newActivity = new Activity("A2", "Activity 2",true);
        process.getTaskList().addEnd(activity);
        assertDoesNotThrow(() -> admin.createActivityGivingName(process, activity, newActivity));
        // Test when the activity is already inside the process
        assertThrows(ProcessException.class, () -> admin.createActivityGivingName(process, activity, newActivity));
    }

    @Test
    void createActivityAtLast() {
        Admin admin = new Admin("admin","a");
        // Test normal case
        MyProcess process = new MyProcess("P1", "Process 1");
        Activity newActivity = new Activity("A2", "Activity 2", true);
        assertDoesNotThrow(() -> admin.createActivityAtLast(process, newActivity));
        // Test when the activity is already inside the process
        assertThrows(ProcessException.class, () -> admin.createActivityAtLast(process, newActivity));
    }

    @Test
    void createActivityUsingLast() {
        Admin admin = new Admin("admin","a");
        // Test normal case
        MyProcess process = new MyProcess("P1", "Process 1");
        Activity lastUsed = new Activity("A1", "Activity 1",true);
        Activity newActivity = new Activity("A2", "Activity 2",true);
        process.getTaskList().addEnd(lastUsed);
        assertDoesNotThrow(() -> admin.createActivityUsingLast(process, lastUsed, newActivity));

        // Test when the activity is already inside the process
        assertThrows(ProcessException.class, () -> admin.createActivityUsingLast(process, lastUsed, newActivity));

        // Test when the last activity does not exist
        assertThrows(ProcessException.class, () -> admin.createActivityUsingLast(process, new Activity("A3", "Activity 3", true), newActivity));
    }

    @Test
    void createTaskAtLast() {
        Admin admin = new Admin("admin","a");

        // Test normal case
        Activity activity = new Activity("A1", "Activity 1", true);
        Task last = new Task("T1", "Task 1", true, LocalDateTime.now());
        Task task = new Task("T2", "Task 2", false,LocalDateTime.now());
        assertDoesNotThrow(() -> admin.createTaskAtLast(activity, last, task));

        // Test when the task already exists in the task list
        assertThrows(ProcessException.class, () -> admin.createTaskAtLast(activity, last, task));

        // Test when there are two non-mandatory tasks together
        Task nonMandatoryTask = new Task("T3", "Task 3", false,LocalDateTime.now());
        assertThrows(ProcessException.class, () -> admin.createTaskAtLast(activity, task, nonMandatoryTask));
    }

    @Test
    void createTaskGivingPosition() {
        Admin admin = new Admin("admin","a");

        // Test normal case
        Activity activity = new Activity("A1", "Activity 1",true);
        Task last = new Task("T1", "Task 1", true,LocalDateTime.now());
        Task task = new Task("T2", "Task 2", false,LocalDateTime.now());
        int position = 0;
        assertDoesNotThrow(() -> admin.createTaskGivingPosition(activity, last, task, position));

        // Test when the task already exists in the activity
        assertThrows(ProcessException.class, () -> admin.createTaskGivingPosition(activity, last, task, position));
        activity.getTasksList().enqueue(task);
        // Test when there are two non-mandatory tasks together
        Task nonMandatoryTask = new Task("T3", "Task 3", false,LocalDateTime.now());
        assertThrows(ProcessException.class, () -> admin.createTaskGivingPosition(activity, task, nonMandatoryTask, position));

        // Test invalid position
        int invalidPosition = 10;
        assertThrows(ProcessException.class, () -> admin.createTaskGivingPosition(activity, last, task, invalidPosition));
    }
}
