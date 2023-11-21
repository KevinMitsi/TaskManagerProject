package com.example.taskmanager.model.mainClass;

import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.exceptions.UserNotFoundException;
import com.example.taskmanager.listas.Cola;
import com.example.taskmanager.listas.SimpleLinkedList;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.User;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TaskManager implements Serializable {
    private final Map<String, User>userMap;
    private final SimpleLinkedList<MyProcess>processes;
    public TaskManager(){
        userMap=new HashMap<>();
        processes = new SimpleLinkedList<>();
    }

    //----------------------Getters Setters----------------------------------------------------------

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public SimpleLinkedList<MyProcess> getProcesses() {
        return processes;
    }

    //----------------------Login----------------------------------------------------------

    public Common login(String username, String password) throws UserNotFoundException {
        User user = userMap.get(password);
        if (user == null || !username.equals(user.getUsername())) {
            throw new UserNotFoundException("Invalid username or password");
        }
        return user.getAssociated();
    }

    //----------------------Register----------------------------------------------------------


    public void registerCommon(User user) throws RegisterException {
        if (userMap.containsValue(user)){
            throw new RegisterException("This user is already crated");
        }
        else{
            userMap.put(user.getPassword(),user);
        }
    }

    //----------------------Change activities----------------------------------------------------------


    public void changeActivities(Activity activity1, Activity activity2){
        Cola<Task>aux = activity1.getTasksList();
        activity1.setTasksList(activity2.getTasksList());
        activity2.setTasksList(aux);
    }
}
