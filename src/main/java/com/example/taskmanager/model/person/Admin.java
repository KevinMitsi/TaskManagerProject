package com.example.taskmanager.model.person;

import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.listas.Cola;
import com.example.taskmanager.listas.SimpleLinkedList;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Admin implements Serializable {
    private String name;
    private String id;
    private  Map<String, Common> employees;
    private  SimpleLinkedList<MyProcess> createdProcesses;

    public Admin(String name, String id) {
        this.name = name;
        this.id = id;
        employees = new HashMap<>();
        createdProcesses = new SimpleLinkedList<>();
    }

    public Admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Common> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<String, Common> employees) {
        this.employees = employees;
    }

    public SimpleLinkedList<MyProcess> getCreatedProcesses() {
        return createdProcesses;
    }

    public void setCreatedProcesses(SimpleLinkedList<MyProcess> createdProcesses) {
        this.createdProcesses = createdProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        return Objects.equals(getId(), admin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public  void assignProcess(Common employee, MyProcess process) throws ProcessException {
        if (employee.getProcesses().containsValue(process)) {
            throw new ProcessException("This process is already assigned in your employee");
        } else {
            employee.getProcesses().put(String.valueOf(employee.getProcesses().size() + 1), process);
        }
    }

    public  void addEmployee(Common common) throws RegisterException {
        if (employees.containsValue(common)) {
            throw new RegisterException("This employee is already added in your list");
        } else {
            employees.put(common.getId(), common);
        }
    }

    public  void createProcess(String id, String name, Common common) throws ProcessException {
        MyProcess process = new MyProcess(id, name);
        if (common.getProcesses().containsValue(process)) {
            throw new ProcessException("This process is already created in this user processes");
        }
        common.getProcesses().put(id,process);
    }

    public  void createActivityGivingName(MyProcess process, Activity activity, Activity newActivity) throws ProcessException {
        if (process.getTaskList().contains(newActivity)) {
            throw new ProcessException("This Activity is already inside this process");
        } else {
            process.getTaskList().addByIndex(process.getTaskList().getIndex(activity) + 1, newActivity);
        }
    }

    public  void createActivityAtLast(MyProcess process, Activity newActivity) throws ProcessException {
        if (process.getTaskList().contains(newActivity)) {
            throw new ProcessException("This Activity is already inside this process");
        } else {
            process.getTaskList().addEnd(newActivity);
        }
    }

    public  void createActivityUsingLast(MyProcess process, Activity lastUsed, Activity newActivity) throws ProcessException {
        if (process.getTaskList().contains(newActivity)) {
            throw new ProcessException("This activity is already inside this process");
        } else {
            if (!process.getTaskList().contains(lastUsed)) {
                throw new ProcessException("This last activity does not exist");
            }
            process.getTaskList().addByIndex(process.getTaskList().getIndex(lastUsed) + 1, newActivity);
        }
    }

    public void createTaskAtLast(Activity activity, Task last, Task task) throws ProcessException {
        if (last==null){
            activity.getTasksList().enqueue(task);
        }
        if (last!=null && activity.getTasksList().contains(task)) {
            throw new ProcessException("The task Already exist in the Task Queue");
        }
        if(last!=null&&!last.isMandatory() && !task.isMandatory()){
            throw new ProcessException("There cannot be two non mandatory task together");
        }
        if (last!=null) {
            activity.getTasksList().enqueue(task);
        }
    }

    public void createTaskGivingPosition(Activity activity, Task last,Task task) throws ProcessException {
            if (activity.getTasksList().contains(task)){
                throw new ProcessException("This task is already inside this activity");
            }
            if(!last.isMandatory() && !task.isMandatory()){
                throw new ProcessException("There cannot be two non mandatory task together");
            }
            activity.getTasksList().insertAt(task, activity.getTasksList().getIndex(last));
    }
}
