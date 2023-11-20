package com.example.taskmanager.model.person;

import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @Test
    void searchTask() {
        Common common = new Common("John Doe","1", "asdaas@gmail.com");
        MyProcess process1 = new MyProcess("P1", "Process 1");
        MyProcess process2 = new MyProcess("P2", "Process 2");
        Task task1 = new Task("T1", "Task 1 description", true, LocalDateTime.now());
        Task task2 = new Task("T2", "Task 2 description", false, LocalDateTime.now());

        Activity activity1 = new Activity("A1", "Activity 1",true);
        Activity activity2 = new Activity("A2", "Activity 2",true);

        activity1.getTasksList().enqueue(task1);
        activity2.getTasksList().enqueue(task2);

        process1.getTaskList().addEnd(activity1);
        process2.getTaskList().addEnd(activity2);

        common.getProcesses().put(process1.getId(),process1);
        common.getProcesses().put(process2.getId(),process2);

        // Test when tasks with the matching word are found
        List<Task> result1 = common.searchTask("description");
        assertEquals(2, result1.size());
        assertTrue(result1.contains(task1));
        assertTrue(result1.contains(task2));

        // Test when no tasks with the matching word are found
        List<Task> result2 = common.searchTask("NonExistentDescription");
        assertTrue(result2.isEmpty());
    }

    @Test
    void searchActivity() {
        Common common = new Common("John Doe","1", "asdaas@gmail.com");
        MyProcess process1 = new MyProcess("P1", "Process 1");
        MyProcess process2 = new MyProcess("P2", "Process 2");

        Activity activity1 = new Activity("A1", "Activity 1 description",false);
        Activity activity2 = new Activity("A2", "Activity 2 description",false);

        process1.getTaskList().addEnd(activity1);
        process2.getTaskList().addEnd(activity2);

        common.getProcesses().put(process1.getId(),process1);
        common.getProcesses().put(process2.getId(),process2);

        // Test when activities with the matching word are found
        List<Activity> result1 = common.searchActivity("description");
        assertEquals(2, result1.size());
        assertTrue(result1.contains(activity1));
        assertTrue(result1.contains(activity2));

        // Test when no activities with the matching word are found
        List<Activity> result2 = common.searchActivity("NonExistentDescription");
        assertTrue(result2.isEmpty());
    }
}